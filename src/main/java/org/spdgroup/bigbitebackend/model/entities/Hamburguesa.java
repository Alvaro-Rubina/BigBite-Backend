package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter @Setter
@Entity
@DiscriminatorValue("HAMBURGUESA")
public class Hamburguesa extends Producto {

    //
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")  // Esta columna será generada en DetalleInsumo
    private List<DetalleInsumo> insumos;

}
