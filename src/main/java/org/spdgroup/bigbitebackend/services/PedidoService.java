package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleProductoDTO;
import org.spdgroup.bigbitebackend.model.dtos.FacturaDTO;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleProducto;
import org.spdgroup.bigbitebackend.model.entities.Pedido;
import org.spdgroup.bigbitebackend.repositories.PedidoRepository;
import org.spdgroup.bigbitebackend.utils.exception.PedidoNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private DetalleProductoService detalleProductoService;

    //
    public void registrarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);

        List<DetalleProducto> detalleProductos = new ArrayList<>();
        for (DetalleProductoDTO detalleProductoDTO : pedidoDTO.getDetallesProductos()) {
            DetalleProducto detalleProducto = detalleProductoService.registrarDetalleProducto(detalleProductoDTO);
            detalleProductos.add(detalleProducto);
        }

        pedido.setProductos(detalleProductos);
        pedido.setFechaSolicitado(LocalDate.now());
        pedido.setHoraSolicitado(LocalTime.now());
        pedido.setProductos(detalleProductos);

        pedidoRepo.save(pedido);
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Hamburguesa no encontrada"));
    }

    public List<Pedido> obtenerPedidos(){
        return pedidoRepo.findAll();
    }

    public FacturaDTO emitirFactura(Long id){
        Pedido pedido = obtenerPedidoPorId(id);

        FacturaDTO facturaDTO = FacturaDTO.builder()
                .fechaSolicitado(pedido.getFechaSolicitado())
                .fechaEmisionFactura(LocalTime.now())
                .subTotal(pedido.getSubTotal())
                .Total(pedido.getSubTotal())
                .build();

        return facturaDTO;
    }

    public void editarPedido(PedidoDTO pedidoDTO, Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido no encontrado"));

        pedido.setEstadoPedido(pedidoDTO.getEstadoPedido());
        pedidoRepo.save(pedido);
    }
}
