package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class PapasFritas extends Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Tamanio tamanio;
    // TODO: AGREGAR MAS ATRIBUTOS PARA PAPAS FRITAS

    @ManyToMany
    private List<Ingrediente> ingredientes;
}
