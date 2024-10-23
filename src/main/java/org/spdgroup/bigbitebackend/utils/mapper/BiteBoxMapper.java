package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.spdgroup.bigbitebackend.model.dtos.BiteBoxDTO;
import org.spdgroup.bigbitebackend.model.entities.BiteBox;

@Mapper(componentModel = "spring", uses = HamburguesaMapper.class)
public interface BiteBoxMapper {

    @Mapping(target = "hamburguesa", ignore = true)
    @Mapping(target = "bebida", ignore = true)
    BiteBox toEntity(BiteBoxDTO dto);
}
