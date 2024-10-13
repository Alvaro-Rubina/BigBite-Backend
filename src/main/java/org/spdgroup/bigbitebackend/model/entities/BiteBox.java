package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class BiteBox extends Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
