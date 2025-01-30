package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CuentaAsiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id") // ANTES DECIA cuenta_nombre PERO SE MUESTRA EL ID, LO VEO MEJOR DE ESTA FORMA
    private Cuenta cuenta;
}
