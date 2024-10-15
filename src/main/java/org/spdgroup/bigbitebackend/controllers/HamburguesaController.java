package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.services.HamburguesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/hamburguesas")
public class HamburguesaController {

    @Autowired
    private HamburguesaService hamburguesaService;

    @GetMapping
    public List<Hamburguesa> obtenerHamburguesas() {
        return hamburguesaService.obtenerHamburguesas();
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> registrarHamburguesa (@RequestPart HamburguesaDTO hamburguesaDTO,
                                                        @RequestPart MultipartFile imagenHamburguesa) {
        try {
            hamburguesaService.registrarHamburguesa(hamburguesaDTO, imagenHamburguesa);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarHamburguesa(@RequestPart HamburguesaDTO hamburguesaDTO,
                                                    @RequestPart(required = false) MultipartFile imagenHamburguesa,
                                                    @PathVariable Long id) {
        try {
            hamburguesaService.editarHamburguesa(hamburguesaDTO, imagenHamburguesa, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
