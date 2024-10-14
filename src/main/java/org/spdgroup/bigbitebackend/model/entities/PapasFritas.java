package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@DiscriminatorValue("PAPAS_FRITAS")
public class PapasFritas extends Producto{

    //
    private Tamanio tamanio;
    private List<String> ingredientes;
    // TODO: AGREGAR MAS ATRIBUTOS PARA PAPAS FRITAS

}
