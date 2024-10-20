package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class InsumoDTO {

    private String nombre;
    private double precio;
    private double stock;
    private String unidadMedida;
}
