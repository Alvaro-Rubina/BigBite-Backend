package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;

@Mapper(componentModel = "spring", uses = CuentaMapper.class)

public interface CuentaMapper {
    Cuenta toEntity(CuentaDTO cuentaDTO);
    CuentaDTO toDTO(Cuenta cuenta);
}

