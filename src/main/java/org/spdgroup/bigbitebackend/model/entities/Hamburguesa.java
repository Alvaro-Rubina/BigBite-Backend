package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@DiscriminatorValue("HAMBURGUESA")
public class Hamburguesa extends Producto {

    //
    // TODO: AGREGAR MAS ATRIBUTOS PARA HAMBURGUESAAA

    @ManyToMany
    private List<Ingrediente> ingredientes;

}
