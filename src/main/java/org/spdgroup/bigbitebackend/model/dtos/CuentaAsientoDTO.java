package org.spdgroup.bigbitebackend.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CuentaAsientoDTO {
    private long id;
    private double monto;
    private String tipo;
    private Long cuentaId;
}
