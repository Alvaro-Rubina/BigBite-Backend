package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.entities.Usuario;
import org.spdgroup.bigbitebackend.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/save")
    public void saveUsuario(@RequestBody Usuario usuario){
        usuarioService.saveUsuario(usuario);
    }
}
