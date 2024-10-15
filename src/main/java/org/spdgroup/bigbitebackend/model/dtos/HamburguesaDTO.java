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
    private double precio;
    private double preciCombo;
    private int stock;
    private int tiempoPreparacion;
    private String urlImage;
}
