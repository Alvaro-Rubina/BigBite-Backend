package org.spdgroup.bigbitebackend.controllers;

import org.spdgroup.bigbitebackend.model.entities.Producto;
import org.spdgroup.bigbitebackend.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }
}
