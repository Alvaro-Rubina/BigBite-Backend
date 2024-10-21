package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private double monto;
    private String tipo;

    @ManyToOne
    private Cuenta cuenta;

}
