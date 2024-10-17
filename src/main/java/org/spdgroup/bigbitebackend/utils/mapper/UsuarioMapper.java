package org.spdgroup.bigbitebackend.utils.mapper;

import org.mapstruct.Mapper;
import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;
import org.spdgroup.bigbitebackend.model.entities.Usuario;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);
}
