package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;

public interface IUsuarioService {

    public void registrar(UsuarioDTO usuario) throws Exception;
    /*public void iniciarSesion(UsuarioDTO usuario);*/
}
