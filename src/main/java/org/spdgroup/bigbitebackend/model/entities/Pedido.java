package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.spdgroup.bigbitebackend.model.enums.EstadoPedido;
import org.spdgroup.bigbitebackend.model.enums.MetodoPago;

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
    private LocalDate fechaSolicitado;
    private LocalTime horaSolicitado;
    private double subTotal;
    private MetodoPago metodoPago;
    private EstadoPedido estadoPedido;

    @ManyToMany
    private List<DetalleProducto> productos;

    @OneToOne
    private Factura factura;
}
