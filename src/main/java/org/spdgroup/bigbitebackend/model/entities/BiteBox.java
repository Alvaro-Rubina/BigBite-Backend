package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

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

    // Metodos para detalles especificos de BiteBox
    private void setTamanioDeBebida(){
        this.bebida.setTamanio(Tamanio.MEDIANO);
    }

}
