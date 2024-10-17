package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;

@Mapper(componentModel = "spring", uses = BebidaMapper.class)
public interface BebidaMapper {

    Bebida toEntity(BebidaDTO bebidaDTO);
}
