package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.spdgroup.bigbitebackend.model.enums.Marca;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@DiscriminatorValue("BEBIDA")
public class Bebida extends Producto {

    //
    private Tamanio tamanio;
    private Marca marca;
    // TODO: AGREGAR MAS ATRIBUTOS PARA BEBIDAS
}
