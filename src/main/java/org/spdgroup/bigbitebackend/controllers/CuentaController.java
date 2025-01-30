package org.spdgroup.bigbitebackend.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.CuentaDTO;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;
import org.spdgroup.bigbitebackend.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Cuentas", description = "Metodos para registrar y obtener cuentas")
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Operation(summary = "Devuelve todas las cuentas de la BBDD")
    @GetMapping @ResponseBody
    public List<Cuenta> obtenerCuentas() {
        return cuentaService.obtenerCuentas();
    }

    @Operation(summary = "Registra una nueva cuenta en la BBDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta registrada", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CuentaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Error al registrar la cuenta", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCuenta(@RequestBody @Valid CuentaDTO cuentaDTO) {
        cuentaService.registrarCuenta(cuentaDTO);
        return new ResponseEntity<>("Cuenta registrada exitosamente", HttpStatus.CREATED);
    }

}
