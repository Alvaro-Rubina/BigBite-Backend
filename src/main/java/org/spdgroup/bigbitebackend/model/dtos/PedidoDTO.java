package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PedidoDTO {

    //
    private double subTotal;
    private String metodoPago;
    private String estadoPedido;
    private List<DetalleProductoDTO> detallesProductos;

}
