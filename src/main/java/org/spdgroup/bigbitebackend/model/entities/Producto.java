package org.spdgroup.bigbitebackend.model.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@ToString
public abstract class Producto {

    //
    private String nombre;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private int stockActual;
    private int tiempoPreparacion;
    private boolean disponible;        // True = se puede vender, False = no se puede vender
}
