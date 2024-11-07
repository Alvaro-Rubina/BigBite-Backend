package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebidas")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    //

    @GetMapping
    public List<Bebida> obtenerBebidas() {
        return bebidaService.obtenerBebidas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBebidaPorId(@PathVariable Long id) {
        Bebida bebida = bebidaService.obtenerBebidaPorId(id);
        return ResponseEntity.ok(bebida);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarBebida(@RequestBody BebidaDTO bebidaDTO) {
        try {
            bebidaService.registrarBebida(bebidaDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBebida(@RequestBody BebidaDTO bebidaDTO, @PathVariable Long id) {
        try {
            bebidaService.editarBebida(bebidaDTO, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
