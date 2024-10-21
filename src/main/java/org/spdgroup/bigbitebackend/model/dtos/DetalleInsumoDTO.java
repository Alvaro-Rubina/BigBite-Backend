package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class DetalleInsumoDTO {

    //
    private Long insumoId;
    private int cantidad;
}
