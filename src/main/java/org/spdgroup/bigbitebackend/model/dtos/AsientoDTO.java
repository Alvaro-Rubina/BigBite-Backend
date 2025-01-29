package org.spdgroup.bigbitebackend.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class AsientoDTO {

    @Schema(example = "2023-01-01") @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    @Schema(example = "Asiento de prueba") @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
    @NotNull(message = "Este campo es obligatorio")
    private List<CuentaAsientoDTO> cuentaAsientoDTO = new ArrayList<>();

}
