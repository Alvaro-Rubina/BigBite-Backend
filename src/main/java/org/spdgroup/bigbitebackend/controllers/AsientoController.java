package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    //
    @GetMapping
    public List<Asiento> obtenerAsientos() {
        return asientoService.obtenerAsientos();
    }

    @GetMapping("/cuenta/{id}")
    public List<Asiento> obtenerAsientosPorCuenta(@PathVariable Long id) {
        return asientoService.obtenerAsientosPorCuenta(id);
    }

    @PostMapping("/agregar")
    public void registrarAsiento(@RequestBody AsientoDTO asientoDTO) {
        asientoService.registrarAsiento(asientoDTO);
    }
}
