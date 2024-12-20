package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Pedido {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String titulo;
    private double subTotal;
    private LocalDate fechaSolicitado;
    private LocalTime horaSolicitado;
    private String metodoPago;
    private String tipoEntrega;
    private String estadoPedido;

    @ManyToMany
    private List<Producto> productos;

}
