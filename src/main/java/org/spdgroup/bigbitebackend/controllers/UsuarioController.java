package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;
import org.spdgroup.bigbitebackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // TODO: LUEGO AGREGAR UNA CLASE GlobalExceptionHandler PARA MANEJAR LAS EXCEPCIONES A NIVEL GLOBAL
    // TODO: Implementar autenticacion con google (Colmena TEC) y manual (https://www.youtube.com/watch?v=-Z4a0bKr2Pg)

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestPart("usuario") UsuarioDTO usuario,
                                            @RequestPart("imagenPerfil") MultipartFile imagenPerfil) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.registrarUsuario(usuario, imagenPerfil);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody UsuarioDTO usuario){
        try {
            UsuarioDTO usuarioDTO = usuarioService.autenticarUsuario(usuario);
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
