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
@DiscriminatorValue("PAPAS_FRITAS")
public class PapasFritas extends Producto{

    //
    private String tamanio;
    // TODO: AGREGAR MAS ATRIBUTOS PARA PAPAS FRITAS

}
