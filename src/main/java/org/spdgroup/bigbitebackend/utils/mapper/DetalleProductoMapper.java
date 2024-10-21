package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.spdgroup.bigbitebackend.model.dtos.DetalleProductoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleProducto;

@Mapper(componentModel = "spring", uses = DetalleProductoMapper.class)
public interface DetalleProductoMapper {

    @Mapping(target = "idProducto", ignore = true)
    DetalleProducto toEntity(DetalleProductoDTO detalleProductoDTO);
}
