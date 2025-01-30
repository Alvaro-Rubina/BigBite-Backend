package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.AsientoDTO;
import org.spdgroup.bigbitebackend.model.entities.Asiento;
import org.spdgroup.bigbitebackend.services.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Asientos", description = "Metodos para registrar, obtener y editar asientos contables")
@RequestMapping("/api/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    @Operation(summary = "Devuelve todos los asientos contables de la BBDD")
    @GetMapping @ResponseBody
    public List<Asiento> obtenerAsientos()   {
        return asientoService.obtenerAsientos();
    }

    @Operation(summary = "Registra un nuevo asiento en la BBDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asiento registrado", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AsientoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarAsiento(@RequestBody @Valid AsientoDTO asientoDTO) {
        asientoService.registrarAsiento(asientoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }
}
