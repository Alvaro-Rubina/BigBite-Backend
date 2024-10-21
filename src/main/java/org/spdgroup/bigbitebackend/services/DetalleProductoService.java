package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.DetalleProductoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleProducto;
import org.spdgroup.bigbitebackend.repositories.DetalleProductoRepository;
import org.spdgroup.bigbitebackend.utils.mapper.DetalleProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleProductoService {

    @Autowired
    private DetalleProductoRepository detalleProductoRepo;

    @Autowired
    private DetalleProductoMapper detalleProductoMapper;

    @Autowired
    private ProductoService productoService;

    public DetalleProducto registrarDetalleProducto(DetalleProductoDTO detalleProductoDTO) {
        DetalleProducto detalleProducto = detalleProductoMapper.toEntity(detalleProductoDTO);
        detalleProducto.setProducto(productoService.obtenerProductoPorId(detalleProductoDTO.getIdProducto()));
        return detalleProductoRepo.save(detalleProducto);

    }

    public DetalleProducto obtenerDetalleProductoPorId(Long id) {
        return detalleProductoRepo.findById(id).orElse(null);
    }


}
