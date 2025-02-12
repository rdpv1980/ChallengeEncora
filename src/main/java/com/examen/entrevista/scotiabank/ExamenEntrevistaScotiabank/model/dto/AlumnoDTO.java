package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity.Estado;
import jakarta.validation.constraints.*;

public record AlumnoDTO(
        Long id,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotNull Estado estado,
        @Min(12) @Max(100) int edad
) {

}
