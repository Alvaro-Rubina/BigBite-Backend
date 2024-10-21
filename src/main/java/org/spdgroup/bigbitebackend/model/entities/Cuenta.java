package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Cuenta {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String naturaleza;

    public Cuenta(String codigo, String nombre, String naturaleza) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.naturaleza = naturaleza;
    }
}
