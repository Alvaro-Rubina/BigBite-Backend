package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PedidoDTO {

    //
    private Long id;
    private String fechaSolicitado;
    private String horaSolicitado;
    private double subTotal;
    private String metodoPago;
    private String estadoPedido;
    private List<DetalleProductoDTO> detallesProductos;

}
