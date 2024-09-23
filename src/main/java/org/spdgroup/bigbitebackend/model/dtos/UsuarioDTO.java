package org.spdgroup.bigbitebackend.model.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioDTO {

    //
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String password;
    private String imagen;
}
