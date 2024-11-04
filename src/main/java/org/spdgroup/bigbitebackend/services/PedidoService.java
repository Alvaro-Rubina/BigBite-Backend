package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.FacturaDTO;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.Pedido;
import org.spdgroup.bigbitebackend.model.entities.Producto;
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
}
