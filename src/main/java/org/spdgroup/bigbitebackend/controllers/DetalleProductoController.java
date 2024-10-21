package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.dtos.DetalleProductoDTO;
import org.spdgroup.bigbitebackend.model.entities.DetalleProducto;
import org.spdgroup.bigbitebackend.services.DetalleProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/detalles-producto")
public class DetalleProductoController {

    @Autowired
    private DetalleProductoService detalleProductoService;

    @PostMapping("/agregar")
    public ResponseEntity<?> registrarDetalleProducto(DetalleProductoDTO detalleProducto) {
        detalleProductoService.registrarDetalleProducto(detalleProducto);
        return ResponseEntity.ok("Detalle de producto registrado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetalleProductoPorId(Long id) {
        DetalleProducto detalleProducto = detalleProductoService.obtenerDetalleProductoPorId(id);
        return ResponseEntity.ok(detalleProducto);
    }
}
