package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.dtos.AsientoPorDiaDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/cuenta/{codigo}")
    public List<Asiento> obtenerAsientosPorCuenta(@PathVariable String codigo) {
        return asientoService.obtenerAsientosPorCuenta(codigo);
    }

    @PostMapping("/agregar")
    public void registrarAsiento(@RequestBody AsientoDTO asientoDTO) {
        asientoService.registrarAsiento(asientoDTO);
    }

    @GetMapping("/totalesPorDia")
    public List<AsientoPorDiaDTO> obtenerTotalesPorDia(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return asientoService.obtenerSumaAsientosPorDia(fechaInicio, fechaFin);
    }
}
