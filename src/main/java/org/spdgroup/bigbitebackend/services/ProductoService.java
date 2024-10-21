package org.spdgroup.bigbitebackend.services;

import org.spdgroup.bigbitebackend.model.entities.Producto;
import org.spdgroup.bigbitebackend.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    public List<Producto> obtenerProductos() {
        return productoRepo.findAll();
    }
}
