package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.spdgroup.bigbitebackend.model.enums.Marca;
import org.spdgroup.bigbitebackend.model.enums.Tamanio;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Bebida extends Producto {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Tamanio tamanio;
    private Marca marca;
    // TODO: AGREGAR MAS ATRIBUTOS PARA BEBIDAS
}
