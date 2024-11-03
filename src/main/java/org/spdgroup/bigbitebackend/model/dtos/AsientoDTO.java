package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AsientoDTO {

    private Long id;
    private LocalDate fecha;
    private String descripcion;
    private List<CuentaAsientoDTO> cuentaAsientoDTO = new ArrayList<>();

}
