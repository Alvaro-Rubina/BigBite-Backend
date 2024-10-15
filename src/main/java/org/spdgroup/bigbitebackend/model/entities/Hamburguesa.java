package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Getter @Setter
@Entity
@DiscriminatorValue("HAMBURGUESA")
public class Hamburguesa extends Producto {

    // TODO: AGREGAR MAS ATRIBUTOS PARA HAMBURGUESAAA

}
