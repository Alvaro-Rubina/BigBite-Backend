package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.entities.Producto;
import org.spdgroup.bigbitebackend.repositories.ProductoRepository;
import org.spdgroup.bigbitebackend.utils.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    public Producto obtenerProductoPorId(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
    }

    public List<Producto> obtenerProductos() {
        return productoRepo.findAll();
    }
}
