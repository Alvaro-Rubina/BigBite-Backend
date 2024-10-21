package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class DetalleProductoDTO {

        private int cantidad;
        private Long idProducto;
}
