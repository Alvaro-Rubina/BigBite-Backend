package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleProductoDTO;
import org.spdgroup.bigbitebackend.model.dtos.PedidoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleProducto;
import org.spdgroup.bigbitebackend.model.entities.Pedido;
import org.spdgroup.bigbitebackend.repositories.PedidoRepository;
import org.spdgroup.bigbitebackend.utils.exception.PedidoNotFoundException;
import org.spdgroup.bigbitebackend.utils.mapper.PedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        List<DetalleProducto> detalleProductos = null;
        for (DetalleProductoDTO detalleProductoDTO : pedidoDTO.getDetallesProductos()) {
            DetalleProducto detalleProducto = detalleProductoService.registrarDetalleProducto(detalleProductoDTO);
            detalleProductos.add(detalleProducto);
        }

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

    // TODO: NO ESTAN ?????
}
