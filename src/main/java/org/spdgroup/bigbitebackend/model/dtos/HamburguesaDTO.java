package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class HamburguesaDTO {

    //
    private String nombre;
    private String descripcion;
    private double precio;
    private double precioCombo;
    private int tiempoPreparacion;
    private boolean disponible;
    private List<DetalleInsumoDTO> detalleInsumos;

}
