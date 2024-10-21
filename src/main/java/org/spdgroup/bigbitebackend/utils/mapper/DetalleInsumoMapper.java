package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.DetalleInsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleInsumo;

@Mapper(componentModel = "spring", uses = InsumoMapper.class)
public interface DetalleInsumoMapper {

    DetalleInsumo toEntity(DetalleInsumoDTO dto);
}
