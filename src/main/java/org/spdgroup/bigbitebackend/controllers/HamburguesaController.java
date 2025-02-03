package org.spdgroup.bigbitebackend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.spdgroup.bigbitebackend.model.dtos.HamburguesaDTO;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;
import org.spdgroup.bigbitebackend.services.HamburguesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Hamburguesas", description = "Metodos para realizar distintas operaciones con hamburguesas. A continuación se ejemplifica como interactuar con cada endpoint.")
@RequestMapping("/api/hamburguesas")
public class HamburguesaController {

    @Autowired
    private HamburguesaService hamburguesaService;

    @Operation(summary = "Devuelve todas las hamburguesas de la BBDD")
    @GetMapping @ResponseBody
    public List<Hamburguesa> obtenerHamburguesas() {
        return hamburguesaService.obtenerHamburguesas();
    }

    @Operation(summary = "Busca una hamburguesa por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hamburguesa encontrada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HamburguesaDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Hamburguesa no encontrada", content = @Content)
    })
    @GetMapping("/{id}") @ResponseBody
    public ResponseEntity<?> obtenerHamburguesaPorId(@Parameter(description = "ID de la hamburguesa", example = "1") @PathVariable Long id) {
        Hamburguesa hamburguesa = hamburguesaService.obtenerHamburguesaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(hamburguesa);
    }

    @Operation(summary = "Registra una nueva hamburguesa en la BBDD")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Hamburguesa registrada", content = {
                    @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = HamburguesaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Insumo con el ID ingresado no encontrado")
    })
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarHamburguesa(@RequestBody @Valid HamburguesaDTO hamburguesaDTO) {
        hamburguesaService.registrarHamburguesa(hamburguesaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }

    @Operation(summary = "Edita una hamburguesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hamburguesa editada", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HamburguesaDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Hamburguesa no encontrada", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarHamburguesa(@RequestBody HamburguesaDTO hamburguesaDTO, @PathVariable Long id) {
        hamburguesaService.editarHamburguesa(hamburguesaDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body("Edicion exitosa");
    }

}
