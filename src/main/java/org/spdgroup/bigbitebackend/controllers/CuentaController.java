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
@Tag(name = "Cuentas", description = "Métodos para registrar y obtener cuentas. Por defecto, las cuentas ya comienzan cargadas en Big Bite. A continuación se ejemplifica el uso de estos endpoints para un mejor entendimiento." +
        "\n[A tener en cuenta]: Se recomienda comprender esta sección, pero ponerla en práctica desde la página web de Big Bite." )
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
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCuenta(@RequestBody @Valid CuentaDTO cuentaDTO) {
        cuentaService.registrarCuenta(cuentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

}
