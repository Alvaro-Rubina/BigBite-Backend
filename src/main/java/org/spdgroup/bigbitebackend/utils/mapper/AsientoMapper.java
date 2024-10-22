package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;

@Mapper(componentModel = "spring", uses = AsientoMapper.class)
public interface AsientoMapper {

    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target="fecha", ignore = true)
    Asiento toEntity(AsientoDTO asientoDTO);
}
