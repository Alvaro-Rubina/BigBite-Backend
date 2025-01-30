package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class CuentaDTO {

    @Schema(example = "2.1.1.01") @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @Schema(example = "Proveedores") @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Schema(example = "Pasivo") @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipoCuenta;

}
