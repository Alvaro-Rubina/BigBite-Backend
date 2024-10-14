package org.spdgroup.bigbitebackend.model.dtos.request;

public record HamburguesaDTO(String nombre,
                             String descripcion,
                             double precioCosto,
                             double precioVenta,
                             int stockActual,
                             int tiempoPreparacion,
                             String urlImagen){
}
