package org.spdgroup.bigbitebackend.model.dtos;

import org.spdgroup.bigbitebackend.model.enums.Tamanio;

import java.util.List;

public record PapasFritasDTO(String nombre,
                             String descripcion,
                             double precioCosto,
                             double precioVenta,
                             int stockActual,
                             int tiempoPreparacion,
                             Tamanio tamanio,
                             List<String> ingredientes,
                             String urlImagen) {
}
