package org.spdgroup.bigbitebackend.model.dtos.request;

import org.spdgroup.bigbitebackend.model.enums.Tamanio;

public record BebidaRequest(String nombre,
                            String descripcion,
                            double precioCosto,
                            double precioVenta,
                            int stockActual,
                            int tiempoPreparacion,
                            Tamanio tamanio) {
}
