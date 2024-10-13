package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private boolean activo;        // True = se puede vender, False = no se puede vender
}
