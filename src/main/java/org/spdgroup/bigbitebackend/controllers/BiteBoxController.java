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
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/bite-box")
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

    @PostMapping("/agregar")
    public ResponseEntity<String> registrarBiteBox(@RequestPart BiteBoxDTO biteBoxDTO,
                                                   @RequestPart MultipartFile imagen) {
        try {
            biteBoxService.registrarBiteBox(biteBoxDTO, imagen);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBiteBox(@RequestPart("biteBoxDTO") BiteBoxDTO biteBoxDTO,
                                                @RequestPart(value = "imagen", required = false) MultipartFile imagen,
                                                @PathVariable Long id) {
        try {
            biteBoxService.editarBiteBox(biteBoxDTO, imagen, id);
            return new ResponseEntity<>("Edición exitosa", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
