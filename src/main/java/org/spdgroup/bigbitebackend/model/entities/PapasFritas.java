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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")  // Esta columna será generada en DetalleInsumo
    private List<DetalleInsumo> insumos;

}
