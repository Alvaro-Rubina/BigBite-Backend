package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class CuentaAsiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double monto;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cuenta_nombre")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "asiento_id")
    @JsonBackReference // Anotación para evitar referencia circular
    private Asiento asiento;
}
