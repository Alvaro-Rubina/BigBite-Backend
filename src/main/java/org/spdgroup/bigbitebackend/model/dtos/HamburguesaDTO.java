package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class HamburguesaDTO {

    //
    @Schema(example = "Mega Crunch Bite") @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(example = "La mas CLASICA hamburguesa de Big Bite! MEGA grande y MEGA deliciosa.") @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @Schema(example = "4000", description = "(Precio de costo)") @NotNull(message = "El precio de costo (precio) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de costo (precio) es 0")
    private Double precio;

    @Schema(example = "7500", description = "(Precio de venta)") @NotNull(message = "El precio de venta (precioCombo) es obligatorio")
    @Min(value = 0, message = "El valor minimo del precio de venta (precioCombo) es 0")
    private Double precioCombo;

    @Schema(example = "15", description = "Tiempo en minutos") @NotNull(message = "El tiempo de preparacion es obligatorio")
    @Min(value = 0, message = "El valor minimo del tiempo de preparacion es 0")
    private Integer tiempoPreparacion;


    @Schema(example = "true", description = "Por defecto es false, si no se especifica en el JSON, tomará dicho valor")
    private boolean disponible;

    @Schema(example = "https://images.pexels.com/photos/15476354/pexels-photo-15476354/free-photo-of-hamburguesa.jpeg")
    private String urlImagen;

    @NotNull(message = "La lista de DetalleInsumos es obligatoria")
    @Valid
    private List<DetalleInsumoDTO> detalleInsumos;

}
