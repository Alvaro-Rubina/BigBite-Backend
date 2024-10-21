package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.InsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.Insumo;

@Mapper(componentModel = "spring", uses = InsumoMapper.class)
public interface InsumoMapper {

    Insumo toEntity (InsumoDTO insumoDTO);

    InsumoDTO toDTO (Insumo insumo);
}
