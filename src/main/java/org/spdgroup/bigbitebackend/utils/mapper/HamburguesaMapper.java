package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.spdgroup.bigbitebackend.model.dtos.request.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;

@Mapper(componentModel = "spring", uses = HamburguesaMapper.class)
public interface HamburguesaMapper {

    Hamburguesa hamburguesaDTOToHamburguesa(HamburguesaDTO hamburguesaDTO);

    @AfterMapping
    default void setDisponible(@MappingTarget Hamburguesa hamburguesa) {
        hamburguesa.setDisponible();
    }
}
