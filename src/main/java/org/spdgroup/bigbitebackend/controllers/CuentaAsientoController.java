package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.spdgroup.bigbitebackend.services.CuentaAsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentasAsiento")
public class CuentaAsientoController {
    @Autowired
    private CuentaAsientoService cuentaAsientoService;

    @GetMapping
    public List<CuentaAsiento> obtenerCuentaAsiento() {
        return cuentaAsientoService.obtenerCuentasAsientos();
    }

    @PostMapping("/registrar")

    public void agregarCuentaAsiento(@RequestBody CuentaAsientoDTO cuentaAsientoDTO) {
        cuentaAsientoService.registrarCuentaAsiento(cuentaAsientoDTO);
    }

}
