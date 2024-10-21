package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@DiscriminatorValue("BEBIDA")
public class Bebida extends Producto {

    //
    private int stock;
    private String marca;
    // TODO: AGREGAR MAS ATRIBUTOS PARA BEBIDAS
}
