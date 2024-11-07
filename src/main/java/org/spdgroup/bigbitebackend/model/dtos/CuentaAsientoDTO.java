package org.spdgroup.bigbitebackend.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CuentaAsientoDTO {
    private long id;
    private double monto;
    private String tipo;
    private Long cuentaId;

    public CuentaAsientoDTO(double monto, String tipo, Long cuentaId) {
        this.monto = monto;
        this.tipo = tipo;
        this.cuentaId = cuentaId;
    }


}
