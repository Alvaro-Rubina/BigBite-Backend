package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AsientoDTO {

    private Date fecha;
    private double monto;
    private Long cuenta;
    private String tipo;
}
