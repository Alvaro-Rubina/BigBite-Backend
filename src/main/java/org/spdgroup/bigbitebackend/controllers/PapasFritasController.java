package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.PapasFritasDTO;
import org.spdgroup.bigbitebackend.model.entities.PapasFritas;
import org.spdgroup.bigbitebackend.services.PapasFritasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/papas-fritas")
public class PapasFritasController {

    @Autowired
    private PapasFritasService papasFritasService;

    @GetMapping
    public List<PapasFritas> obtenerPapasFritas() {
        return papasFritasService.obtenerPapasFritas();
    }

    @GetMapping("/{id}")
    public PapasFritas obtenerPapasFritasPorId(@PathVariable Long id) {
        return papasFritasService.obtenerPapasFritasPorId(id);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPapasFritas(@RequestBody PapasFritasDTO papasFritasDTO) {
        try {
            papasFritasService.registrarPapasFritas(papasFritasDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarPapasFritas(@RequestBody PapasFritasDTO papasFritasDTO, @PathVariable Long id) {
        try {
            papasFritasService.editarPapasFritas(papasFritasDTO, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
