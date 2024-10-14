package org.spdgroup.bigbitebackend.model.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
public class DetalleProducto {

    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;

    // TODO: VER COMO LOGRAR ESTO TENIENDO EN CUENTA LA HERENCIA etc ma{ñana veo
    @ManyToOne
    private Producto producto;
}
