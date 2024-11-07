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
        Cuenta cuentaMateriasPrimas = cuentaRepo.findByNombre("Materias Primas");

        // Verifica que todas las cuentas estén presentes
        if (cuentaVentas == null || cuentaCostoMercaderia == null || cuentaMetodoPago == null || cuentaMateriasPrimas == null) {
            throw new IllegalArgumentException("Una o más cuentas no se encuentran en la base de datos");
        }

        // Calcula el costo total de los productos
        double costoTotalProductos = pedido.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        // Registra el asiento de ventas
        List<CuentaAsientoDTO> cuentaAsientoDTOsVenta = List.of(
                new CuentaAsientoDTO(pedido.getSubTotal(), "haber", cuentaVentas.getId()),
                new CuentaAsientoDTO(pedido.getSubTotal(), "debe", cuentaMetodoPago.getId())
        );

        AsientoDTO asientoVenta = AsientoDTO.builder()
                .fecha(pedido.getFechaSolicitado())
                .descripcion("Asiento de ventas generado por pedido " + pedido.getId())
                .cuentaAsientoDTO(cuentaAsientoDTOsVenta)
                .build();

        asientoService.registrarAsiento(asientoVenta);

        // Registra el asiento de costo de mercaderías vendidas
        List<CuentaAsientoDTO> cuentaAsientoDTOsCosto = List.of(
                new CuentaAsientoDTO(costoTotalProductos, "debe", cuentaCostoMercaderia.getId()),
                new CuentaAsientoDTO(costoTotalProductos, "haber", cuentaMateriasPrimas.getId())
        );

        AsientoDTO asientoCosto = AsientoDTO.builder()
                .fecha(pedido.getFechaSolicitado())
                .descripcion("Asiento de costo de mercaderías vendidas por pedido " + pedido.getId())
                .cuentaAsientoDTO(cuentaAsientoDTOsCosto)
                .build();

        asientoService.registrarAsiento(asientoCosto);
    }

}



