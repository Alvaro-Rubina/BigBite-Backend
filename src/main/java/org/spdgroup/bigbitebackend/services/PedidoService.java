package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.dtos.FacturaDTO;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.*;
import org.spdgroup.bigbitebackend.repositories.AsientoRepository;
import org.spdgroup.bigbitebackend.repositories.ICuentaRepository;
import org.spdgroup.bigbitebackend.repositories.PedidoRepository;
import org.spdgroup.bigbitebackend.repositories.ProductoRepository;
import org.spdgroup.bigbitebackend.utils.exception.PedidoNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ICuentaRepository cuentaRepo;

    @Autowired
    private AsientoService asientoService;

    //
    public void registrarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

        for (Producto producto : pedidoDTO.getDescripcion()) {
            if (!productoRepo.existsById(producto.getId())) {
                throw new IllegalArgumentException("Producto con ID " + producto.getId() + " no existe");
            }
        }

        pedido.setProductos(pedidoDTO.getDescripcion());
        pedido.setFechaSolicitado(LocalDate.now());
        pedido.setHoraSolicitado(LocalTime.now());

        pedidoRepo.save(pedido);
        generarAsientoContable(pedido);
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido no encontrado"));
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public FacturaDTO emitirFactura(Long id){
        Pedido pedido = obtenerPedidoPorId(id);

        return FacturaDTO.builder()
                .fechaSolicitado(pedido.getFechaSolicitado())
                .fechaEmisionFactura(LocalTime.now())
                .subTotal(pedido.getSubTotal())
                .Total(pedido.getSubTotal())
                .build();
    }

    public void editarPedido(PedidoDTO pedidoDTO, Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido no encontrado"));

        pedido.setEstadoPedido(pedidoDTO.getEstadoPedido());
        pedidoRepo.save(pedido);
    }

    public List<Pedido> obtenerPedidoPorEmail(String email) {
        return pedidoRepo.findByEmail(email);
    }


    public void generarAsientoContable(Pedido pedido) {
        // Obtén las cuentas necesarias desde la base de datos usando el repositorio
        Cuenta cuentaVentas = cuentaRepo.findByNombre("Ventas de Hamburguesas");
        Cuenta cuentaCostoMercaderia = cuentaRepo.findByNombre("Costo de Mercadería Vendida");
        Cuenta cuentaMetodoPago = cuentaRepo.findByNombre(pedido.getMetodoPago());

        // Verifica que todas las cuentas estén presentes
        if (cuentaVentas == null || cuentaCostoMercaderia == null || cuentaMetodoPago == null) {
            throw new IllegalArgumentException("Una o más cuentas no se encuentran en la base de datos");
        }

        // Calcula el costo total de los productos
        double costoTotalProductos = pedido.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        double ventasHamburguesas = pedido.getSubTotal() - costoTotalProductos;

        // Crea los DTOs de CuentaAsiento
        List<CuentaAsientoDTO> cuentaAsientoDTOs = List.of(
                new CuentaAsientoDTO(ventasHamburguesas, "haber", cuentaVentas.getId()),
                new CuentaAsientoDTO(costoTotalProductos, "debe", cuentaCostoMercaderia.getId()),
                new CuentaAsientoDTO(pedido.getSubTotal(), "debe", cuentaMetodoPago.getId())
        );

        // Crea el DTO de Asiento
        AsientoDTO asientoDTO = AsientoDTO.builder()
                .fecha(pedido.getFechaSolicitado())
                .descripcion("Asiento generado por pedido " + pedido.getId())
                .cuentaAsientoDTO(cuentaAsientoDTOs)
                .build();

        // Registra el asiento usando el servicio de Asiento
        asientoService.registrarAsiento(asientoDTO);
    }

}



