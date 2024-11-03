package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;

@Mapper(componentModel = "spring", uses = CuentaAsientoMapper.class)
public interface CuentaAsientoMapper {

    @Mapping(target = "tipo", ignore = true)
    CuentaAsiento toEntity(CuentaAsientoDTO cuentaAsientoDTO);

}
