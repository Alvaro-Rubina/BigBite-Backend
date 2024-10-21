package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cuenta {
    @Id
    private String codigo;
    private String nombre;
    private String naturaleza;
}
