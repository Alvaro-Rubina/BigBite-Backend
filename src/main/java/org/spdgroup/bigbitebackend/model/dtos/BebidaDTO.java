package org.spdgroup.bigbitebackend.model.dtos;

import org.spdgroup.bigbitebackend.model.enums.Tamanio;

public record BebidaDTO(String nombre,
                        String descripcion,
                        double precioCosto,
                        double precioVenta,
                        int stockActual,
                        Tamanio tamanio,
                        String urlImagen) {
}
