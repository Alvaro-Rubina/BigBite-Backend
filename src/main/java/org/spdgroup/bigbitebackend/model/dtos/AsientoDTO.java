package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AsientoDTO {

    private double monto;
    private Long cuenta;
    private String tipo;
}
