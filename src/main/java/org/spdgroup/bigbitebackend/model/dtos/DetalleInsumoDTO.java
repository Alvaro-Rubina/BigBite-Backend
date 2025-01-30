package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class DetalleInsumoDTO {

    // TODO: Resolver: NO se validan bien los datos.
    @Schema(example = "2")
    @NotNull(message = "El ID del insumo es obligatorio")
    @Min(value = 1, message = "El valor minimo del ID es 1")
    private Long insumoId;

    @Schema(example = "2", description = "Refiere a la cantidad de dicho insumo teniendo en cuenta su unidad de" +
            " medida, que se encuentra en el mismo insumo")
    @NotNull(message = "La cantidad es obligatoria")
    @DecimalMin(value = "0.0", inclusive = false, message = "La cantidad debe ser mayor que 0")
    private Double cantidad;
}
