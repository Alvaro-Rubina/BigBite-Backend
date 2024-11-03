package org.spdgroup.bigbitebackend.model.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spdgroup.bigbitebackend.model.entities.Cuenta;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CuentaAsientoDTO {
    private long id;
    private double monto;
    private String tipo;
    private CuentaDTO cuentadto;
}
