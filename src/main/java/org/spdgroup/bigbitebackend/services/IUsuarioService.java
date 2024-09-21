package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;

public interface IUsuarioService {

    public void registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;
    public void autenticarUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
