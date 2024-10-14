package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.UsuarioDTO;
import org.spdgroup.bigbitebackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // TODO: LUEGO AGREGAR UNA CLASE ExceptionHandler PARA MANEJAR LAS EXCEPCIONES A NIVEL GLOBAL
    // TODO: Implementar autenticacion con google (Colmena TEC) y manual (https://www.youtube.com/watch?v=-Z4a0bKr2Pg)

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestPart("usuario") UsuarioDTO usuario,
                                            @RequestPart("imagenPerfil") MultipartFile imagenPerfil) {
        try {
            usuarioService.registrarUsuario(usuario, imagenPerfil);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/iniciar-sesion/")
    public ResponseEntity<String> iniciarSesion(@RequestBody UsuarioDTO usuario){
        try {
            usuarioService.autenticarUsuario(usuario);
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
