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
    private int hamburguesa;        // Se guarda el ID de la hamburguesa para buscarla en la base de datos
    private int bebida;             // Se guarda el ID de la bebida para buscarla en la base de datos
}
