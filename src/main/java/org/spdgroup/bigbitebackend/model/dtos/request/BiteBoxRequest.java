package org.spdgroup.bigbitebackend.model.dtos.request;

import org.spdgroup.bigbitebackend.model.entities.Bebida;
import org.spdgroup.bigbitebackend.model.entities.Hamburguesa;

public record BiteBoxRequest(String nombre,
                             String descripcion,
                             double precioCosto,
                             double precioVenta,
                             int stockActual,
                             boolean contieneJuguete,
                             Hamburguesa hamburguesa,
                             Bebida bebida
                             ) {
}
