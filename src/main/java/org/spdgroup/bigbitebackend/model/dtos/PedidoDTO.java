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
    private double price;
    private String estadoPedido;
    private String tipoEntrega;
    private String metodoPago;
    private List<Producto> descripcion; //TODO: Cambiar para que se reciba una lista de IDs en lugar de objetos
}
