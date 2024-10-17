package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.PapasFritasDTO;
import org.spdgroup.bigbitebackend.model.entities.PapasFritas;

@Mapper(componentModel = "spring", uses = PapasFritasMapper.class)
public interface PapasFritasMapper {

    PapasFritas toEntity(PapasFritasDTO papasFritasDTO);
}
