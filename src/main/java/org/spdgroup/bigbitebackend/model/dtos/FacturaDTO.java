package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class FacturaDTO {

    private LocalDate fechaSolicitado;
    private LocalTime fechaEmisionFactura;
    private double subTotal;
    private double Total;
}
