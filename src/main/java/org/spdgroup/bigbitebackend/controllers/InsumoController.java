package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.InsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.Insumo;
import org.spdgroup.bigbitebackend.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public List<Insumo> obtenerInsumos() {
        return insumoService.obtenerInsumos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarInsumo(@RequestBody InsumoDTO insumoDTO) {
        try {
            insumoService.registrarInsumo(insumoDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarInsumo(@RequestBody InsumoDTO insumoDTO, @PathVariable Long id) {
        try {
            insumoService.editarInsumo(insumoDTO, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
