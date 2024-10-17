package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;

@Mapper(componentModel = "spring", uses = HamburguesaMapper.class)
public interface HamburguesaMapper {

    Hamburguesa toEntity(HamburguesaDTO hamburguesaDTO);

}
