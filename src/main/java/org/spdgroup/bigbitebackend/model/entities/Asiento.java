package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Date fecha;
    @OneToOne
private Cuenta cuenta;

}
