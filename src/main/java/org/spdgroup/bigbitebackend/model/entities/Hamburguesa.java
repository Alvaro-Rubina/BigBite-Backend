package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Hamburguesa extends Producto {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: AGREGAR MAS ATRIBUTOS PARA HAMBURGUESAAA

    @ManyToMany
    private List<Ingrediente> ingredientes;

}
