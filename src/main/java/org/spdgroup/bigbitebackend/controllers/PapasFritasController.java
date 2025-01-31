package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.spdgroup.bigbitebackend.model.dtos.PapasFritasDTO;
import org.spdgroup.bigbitebackend.model.entities.PapasFritas;
import org.spdgroup.bigbitebackend.services.PapasFritasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Papas Fritas", description = "Metodos para registrar, obtener y editar papas fritas")
@RequestMapping("/api/papas-fritas")
public class PapasFritasController {

    @Autowired
    private PapasFritasService papasFritasService;

    @Operation(summary = "Devuelve todas las PapasFritas de la BBDD")
    @GetMapping @ResponseBody
    public List<PapasFritas> obtenerPapasFritas() {
        return papasFritasService.obtenerPapasFritas();
    }

    @Operation(summary = "Busca una PapasFritas por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PapasFritas encontrada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PapasFritasDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "PapasFritas no encontrada", content = @Content)
    })
    @GetMapping("/{id}") @ResponseBody
    public PapasFritas obtenerPapasFritasPorId(@Parameter(description = "ID de la PapasFritas", example = "1") @PathVariable Long id) {
        return papasFritasService.obtenerPapasFritasPorId(id);
    }

    @Operation(summary = "Registra una nueva PapasFritas en la BBDD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "PapasFritas registrada", content = {
                    @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = PapasFritasDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Insumo con el ID ingresado no encontrado")
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPapasFritas(@RequestBody @Valid PapasFritasDTO papasFritasDTO) {
        papasFritasService.registrarPapasFritas(papasFritasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

    @Operation(summary = "Edita una PapasFritas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PapasFritas editada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PapasFritasDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "PapasFritas no encontrada", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarPapasFritas(@RequestBody @Valid PapasFritasDTO papasFritasDTO,
                                                    @Parameter(description = "ID de la PapasFritas", example = "1") @PathVariable Long id) {
        papasFritasService.editarPapasFritas(papasFritasDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Edicion exitosa");
    }
}
