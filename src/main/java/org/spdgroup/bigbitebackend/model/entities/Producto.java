package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto", discriminatorType = DiscriminatorType.STRING)
public class Producto {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double precio;
    private double precioCombo;
    private int stock;       // Al cargar un nuevo producto su stock será 0 si no se especifica
    private int tiempoPreparacion;
    private boolean disponible;        // True = se puede vender, False = no se puede vender
    private String urlImagen;
}
