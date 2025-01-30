package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.CuentaAsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.CuentaAsiento;
import org.spdgroup.bigbitebackend.services.CuentaAsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "CuentaAsientos", description = "Metodos para registrar y obtener entidades CuentaAsiento")
@RequestMapping("/api/cuentasAsiento")
public class CuentaAsientoController {

    @Autowired
    private CuentaAsientoService cuentaAsientoService;

    @Operation(summary = "Devuelve todas las CuentasAsiento de la BBDD")
    @GetMapping @ResponseBody
    public List<CuentaAsiento> obtenerCuentaAsientos() {
        return cuentaAsientoService.obtenerCuentasAsientos();
    }

    @Operation(summary = "Registra una nueva CuentaAsiento en la BBDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CuentaAsiento registrada", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CuentaAsientoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "404", description = "Cuenta con el ID ingresado no encontrada")
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> agregarCuentaAsiento(@RequestBody @Valid CuentaAsientoDTO cuentaAsientoDTO) {
        cuentaAsientoService.registrarCuentaAsiento(cuentaAsientoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

}
