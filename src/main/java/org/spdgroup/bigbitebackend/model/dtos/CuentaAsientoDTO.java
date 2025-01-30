package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CuentaAsientoDTO {

    @Schema(example = "20000") @NotNull(message = "El monto es obligatorio")
    private Double monto;
    @Schema(example = "debe") @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
    @Schema(example = "7") @NotNull(message = "El ID de la cuenta es obligatorio")
    private Long cuentaId;
}
