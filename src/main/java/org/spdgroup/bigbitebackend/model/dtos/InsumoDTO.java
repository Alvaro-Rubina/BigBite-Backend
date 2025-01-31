package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class InsumoDTO {

    @Schema(example = "Pan para hamburguesas") @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(example = "200", description = "(Precio de costo)") @NotNull(message = "El precio de costo (precio) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de costo (precio) es 0")
    private Double precio;

    @Schema(example = "600") @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El valor minimo del stock es 0")
    private Integer stock;

    @Schema(example = "UNIDADES") @NotBlank(message = "La unidad de medida es obligatoria")
    private String unidadMedida;
}
