package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.entities.Usuario;
import org.spdgroup.bigbitebackend.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/nuevo")
    public ResponseEntity<Map<String, String>> saveUsuario(@RequestBody Usuario usuario){
        usuarioService.saveUsuario(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario guardado exitosamente");
        return ResponseEntity.ok(response);
    }

}
