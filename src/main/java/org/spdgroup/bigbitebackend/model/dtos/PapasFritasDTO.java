package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class PapasFritasDTO {

    //
    private String nombre;
    private String descripcion;
    private double precio;
    private double precioCombo;
    private int tiempoPreparacion;
    private String tamanio;
    private boolean disponible;
    private String urlImagen;
    private List<DetalleInsumoDTO> detalleInsumos;
}
