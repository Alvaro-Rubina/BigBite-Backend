package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.BiteBoxDTO;
import org.spdgroup.bigbitebackend.model.entities.BiteBox;
import org.spdgroup.bigbitebackend.services.BiteBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bite-box")
public class BiteBoxController {

    @Autowired
    private BiteBoxService biteBoxService;

    @GetMapping
    public List<BiteBox> obtenerBiteBoxes() {
        return biteBoxService.obtenerBiteBoxes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBiteBoxPorId(@PathVariable Long id) {
        BiteBox biteBox = biteBoxService.obtenerBiteBoxPorId(id);
        return ResponseEntity.ok(biteBox);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarBiteBox(@RequestBody BiteBoxDTO biteBoxDTO) {
        try {
            biteBoxService.registrarBiteBox(biteBoxDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBiteBox(@RequestBody BiteBoxDTO biteBoxDTO, @PathVariable Long id) {
        try {
            biteBoxService.editarBiteBox(biteBoxDTO, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
