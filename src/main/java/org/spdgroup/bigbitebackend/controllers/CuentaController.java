package org.spdgroup.bigbitebackend.controllers;
import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> obtenerCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> registrarCuenta(@RequestPart CuentaDTO cuentaDTO) {
        try {
            cuentaService.registrarCuenta(cuentaDTO);
            return new ResponseEntity<>("Registro exitoso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
