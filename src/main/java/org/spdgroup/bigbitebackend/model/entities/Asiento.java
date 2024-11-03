package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@ToString
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "asiento_id")
    private List<CuentaAsiento> cuentasAsiento;

}
