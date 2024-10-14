package org.spdgroup.bigbitebackend.model.dtos.request;

import java.util.List;

public record HamburguesaRequest (String nombre,
                                 String descripcion,
                                 double precioCosto,
                                 double precioVenta,
                                 int stockActual,
                                 int tiempoPreparacion,
                                 List<String> ingredientes,
                                  String urlImagen){
}
