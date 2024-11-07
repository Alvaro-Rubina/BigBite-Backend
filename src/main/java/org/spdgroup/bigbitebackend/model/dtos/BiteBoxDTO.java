package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class BiteBoxDTO {

    private String nombre;
    private String descripcion;
    private double precio;
    private double precioCombo;
    private int stock;
    private boolean disponible;
    private boolean contieneJuguete;
    private String urlImagen;
    private int hamburguesa;
    private int bebida;
}
