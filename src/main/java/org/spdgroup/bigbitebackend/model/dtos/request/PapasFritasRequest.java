package org.spdgroup.bigbitebackend.model.dtos.request;

import org.spdgroup.bigbitebackend.model.enums.Tamanio;

import java.util.List;

public record PapasFritasRequest(String nombre,
                                 String descripcion,
                                 double precioCosto,
                                 double precioVenta,
                                 int stockActual,
                                 int tiempoPreparacion,
                                 Tamanio tamanio,
                                 List<String> ingredientes,
                                 String urlImagen) {
}
