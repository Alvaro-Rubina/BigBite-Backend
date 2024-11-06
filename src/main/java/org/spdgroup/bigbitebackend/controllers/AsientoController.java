package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    // Endpoint para obtener todos los asientos
    @GetMapping
    public List<Asiento> obtenerAsientos() {
        return asientoService.obtenerAsientos();
    }

    // Endpoint para registrar un nuevo asiento contable
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarAsiento(@RequestBody AsientoDTO asientoDTO) {
        try {
            asientoService.registrarAsiento(asientoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Asiento registrado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
