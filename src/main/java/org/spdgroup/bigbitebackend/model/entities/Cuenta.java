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
    @Id
    private String codigo;
    private String nombre;
    private String naturaleza;
}
