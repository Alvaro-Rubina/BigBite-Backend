package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/bebidas")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @GetMapping
    public List<Bebida> obtenerBebidas() {
        return bebidaService.obtenerBebidas();
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> registrarBebida(@RequestPart BebidaDTO bebidaDTO,
                                                  @RequestPart MultipartFile imagenBebida) {
        try {
            bebidaService.registrarBebida(bebidaDTO, imagenBebida);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBebida(@RequestPart("bebidaDTO") BebidaDTO bebidaDTO,
                                               @RequestPart(value = "imagenBebida", required = false) MultipartFile imagenBebida,
                                               @PathVariable Long id) {
        try {
            bebidaService.editarBebida(bebidaDTO, imagenBebida, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
