package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class HamburguesaDTO {

    //
    private String nombre;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private int stockActual;
    private int tiempoPreparacion;
    private String urlImage;
}
