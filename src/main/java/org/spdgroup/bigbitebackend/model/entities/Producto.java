package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@ToString
@Entity
public class Producto {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private int stockActual;
    private int tiempoPreparacion;
    
    @ManyToMany
    private List<Ingrediente> ingredientes;
}
