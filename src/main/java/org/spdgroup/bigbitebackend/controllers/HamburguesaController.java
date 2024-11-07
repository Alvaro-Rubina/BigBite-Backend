package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.services.HamburguesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hamburguesas")
public class HamburguesaController {

    @Autowired
    private HamburguesaService hamburguesaService;

    @GetMapping
    @ResponseBody
    public List<Hamburguesa> obtenerHamburguesas() {
        return hamburguesaService.obtenerHamburguesas();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerHamburguesaPorId(@PathVariable Long id) {
        Hamburguesa hamburguesa = hamburguesaService.obtenerHamburguesaPorId(id);
        return ResponseEntity.ok(hamburguesa);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarHamburguesa(@RequestBody HamburguesaDTO hamburguesaDTO) {
        try {
            hamburguesaService.registrarHamburguesa(hamburguesaDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarHamburguesa(@RequestBody HamburguesaDTO hamburguesaDTO, @PathVariable Long id) {
        try {
            hamburguesaService.editarHamburguesa(hamburguesaDTO, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
