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
public class BiteBoxDTO {

    //
    @Schema(example = "Bite Box") @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(example = "Bite Box preparada especialmente para niños!") @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @Schema(example = "4500", description = "(Precio de costo)") @NotNull(message = "El precio de costo (precio) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de costo (precio) es 0")
    private Double precio;

    @Schema(example = "8000", description = "(Precio de venta)") @NotNull(message = "El precio de venta (precioCombo) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de venta (precioCombo) es 0")
    private Double precioCombo;

    @Schema(example = "150") @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El valor minimo del stock es 0")
    private Integer stock;

    @Schema(example = "true", description = "Por defecto es false, si no se especifica en el JSON, tomará dicho valor")
    private boolean disponible;

    @Schema(example = "https://goo.su/6UySId")
    private String urlImagen;

    @Schema(example = "true", description = "Por defecto es false, si no se especifica en el JSON, tomará dicho valor")
    private boolean contieneJuguete;

    @Schema(example = "2") @NotNull(message = "El ID de la hamburguesa es obligatorio")
    @Min(value = 1, message = "El valor minimo para el ID es 1")
    private Integer hamburguesa;

    @Schema(example = "4") @NotNull(message = "El ID de la bebida es obligatorio")
    @Min(value = 1, message = "El valor minimo para el ID es 1")
    private Integer bebida;
}
