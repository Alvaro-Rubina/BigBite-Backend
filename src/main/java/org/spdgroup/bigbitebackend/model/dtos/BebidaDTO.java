package org.spdgroup.bigbitebackend.model.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class BebidaDTO {

    //
    private String nombre;
    private String descripcion;
    private double precio;
    private double precioCombo;
    private int stock;
    private int tiempoPreparacion;
    @Enumerated(EnumType.STRING)
    private Tamanio tamanio;
    private boolean disponible;
    private String urlImagen;
}
