package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AsientoPorDiaDTO {
    private LocalDate fecha;
    private double ingresos;
    private double egresos;

    public AsientoPorDiaDTO(LocalDate fecha, double ingresos, double egresos) {
        this.fecha = fecha;
        this.ingresos = ingresos;
        this.egresos = egresos;
    }
}
