package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;
import org.spdgroup.bigbitebackend.model.entities.Producto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PedidoDTO {

    //
    private String title;
    private String email;
    private int cantidad;
    private double price;
    private String estadoPedido;
    private String metodoPago;
    private List<Producto> descripcion;
}
