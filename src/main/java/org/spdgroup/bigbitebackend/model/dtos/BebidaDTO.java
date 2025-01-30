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
public class BebidaDTO {

    //
    @Schema(example = "Manaos cola 500ml") @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(example = "Manaos sabor cola de 500ml") @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @Schema(example = "700", description = "(Precio de costo)") @NotNull(message = "El precio de costo (precio) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de costo (precio) es 0")
    private Double precio;

    @Schema(example = "1200", description = "(Precio de venta)") @NotNull(message = "El precio de venta (precioCombo) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de venta (precioCombo) es 0")
    private Double precioCombo;

    @Schema(example = "150") @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El valor minimo del stock es 0")
    private Integer stock;

    @Schema(example = "Manaos") @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @Schema(example = "true", description = "Por defecto es false, si no se especifica en el JSON, tomará dicho valor")
    private boolean disponible;

    @Schema(example = "https://http2.mlstatic.com/D_Q_NP_2X_600009-MLA72123514777_102023-T.webp")
    private String urlImagen;
}
