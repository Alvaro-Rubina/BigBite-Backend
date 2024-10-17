package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@DiscriminatorValue("BITE_BOX")
public class BiteBox extends Producto {

    //
    private boolean contieneJuguete;

    @ManyToOne
    private Hamburguesa hamburguesa;

    @ManyToOne
    private Bebida bebida;
}
