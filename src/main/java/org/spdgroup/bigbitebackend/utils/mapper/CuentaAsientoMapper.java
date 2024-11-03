package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;

@Mapper(componentModel = "spring", uses = CuentaAsientoMapper.class)
public interface CuentaAsientoMapper {
    CuentaAsiento toEntity(CuentaAsientoDTO cuentaAsientoDTO);

}
