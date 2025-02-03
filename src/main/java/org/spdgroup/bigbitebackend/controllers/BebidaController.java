package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.BebidaDTO;
import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Bebidas", description = "Metodos para realizar distintas operaciones con Bebidas. A continuación se ejemplifica como interactuar con cada endpoint.")
@RequestMapping("/api/bebidas")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    //
    @Operation(summary = "Devuelve todas las bebidas de la BBDD")
    @GetMapping @ResponseBody
    public List<Bebida> obtenerBebidas() {
        return bebidaService.obtenerBebidas();
    }

    @Operation(summary = "Registra una nueva bebida en la BBDD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Bebida registrada", content = {
                    @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = BebidaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarBebida(@RequestBody @Valid BebidaDTO bebidaDTO) {
        bebidaService.registrarBebida(bebidaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

    @Operation(summary = "Busca una bebida por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebida encontrada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BebidaDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Bebida no encontrada", content = @Content)
    })
    @GetMapping("/{id}") @ResponseBody
    public ResponseEntity<?> obtenerBebidaPorId(@Parameter(description = "ID de la bebida", example = "1") @PathVariable Long id) {
        Bebida bebida = bebidaService.obtenerBebidaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(bebida);
    }

    @Operation(summary = "Edita una bebida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bebida editada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BebidaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bebida no encontrada", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBebida(@RequestBody @Valid BebidaDTO bebidaDTO,
                                               @Parameter(description = "ID de la bebida", example = "1") @PathVariable Long id) {
        bebidaService.editarBebida(bebidaDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Edicion exitosa");
    }
}
