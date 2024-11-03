package org.spdgroup.bigbitebackend.model.dtos;

import jakarta.persistence.ManyToOne;
import lombok.*;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AsientoDTO {

    private Long id;
    private double monto;    private LocalDate fecha;
    private String descripcion;

     private List<CuentaAsientoDTO> cuentaAsientoDTO;

}
