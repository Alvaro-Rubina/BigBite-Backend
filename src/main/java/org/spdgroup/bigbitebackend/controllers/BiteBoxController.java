package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.BiteBoxDTO;
import org.spdgroup.bigbitebackend.model.entities.BiteBox;
import org.spdgroup.bigbitebackend.services.BiteBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "BiteBox", description = "Metodos para registrar, obtener y editar Bite Boxes")
@RequestMapping("/api/bite-box")
public class BiteBoxController {

    @Autowired
    private BiteBoxService biteBoxService;

    @Operation(summary = "Devuelve todas las Bite Boxes de la BBDD")
    @GetMapping
    public List<BiteBox> obtenerBiteBoxes() {
        return biteBoxService.obtenerBiteBoxes();
    }

    @Operation(summary = "Busca una Bite Box por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bite Box encontrada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BiteBoxDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Bite Box no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBiteBoxPorId(@Parameter(description = "ID de la Bite Box", example = "1") @PathVariable Long id) {
        BiteBox biteBox = biteBoxService.obtenerBiteBoxPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(biteBox);
    }

    @Operation(summary = "Registra una nueva Bite Box en la BBDD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Bite Box registrada", content = {
                    @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = BiteBoxDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bebida o Hamburguesa con el ID ingresado no encontrada", content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarBiteBox(@RequestBody @Valid BiteBoxDTO biteBoxDTO) {
        biteBoxService.registrarBiteBox(biteBoxDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Registro exitoso");
    }

    @Operation(summary = "Edita una Bite Box")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bite Box editada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BiteBoxDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bite Box no encontrada", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarBiteBox(@RequestBody @Valid BiteBoxDTO biteBoxDTO,
                                                @Parameter(description = "ID de la Bite Box", example = "1") @PathVariable Long id) {
        biteBoxService.editarBiteBox(biteBoxDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Edicion exitosa");
    }
}
