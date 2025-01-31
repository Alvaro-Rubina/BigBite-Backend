package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.InsumoDTO;
import org.spdgroup.bigbitebackend.model.entities.Insumo;
import org.spdgroup.bigbitebackend.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Insumos", description = "Metodos para registrar, obtener y editar insumos")
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @Operation(summary = "Devuelve todos los insumos de la BBDD")
    @GetMapping @ResponseBody
    public List<Insumo> obtenerInsumos() {
        return insumoService.obtenerInsumos();
    }

    @Operation(summary = "Registra un nuevo insumo en la BBDD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Insumo registrado", content = {
                    @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = InsumoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarInsumo(@RequestBody @Valid InsumoDTO insumoDTO) {
        insumoService.registrarInsumo(insumoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

    @Operation(summary = "Edita un insumo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Insumo editado", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InsumoDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Insumo no encontrado", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarInsumo(@RequestBody @Valid InsumoDTO insumoDTO,
                                               @Parameter(description = "ID del insumo", example = "1") @PathVariable Long id) {
        insumoService.editarInsumo(insumoDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Edicion exitosa");
    }
}
