package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import jakarta.validation.constraints.*;

public record AlumnoDTO(
        Long id,
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotNull
        Estado estado,
        @Min(value = 12, message = "La edad mínima permitida es 12 años")
        @Max(value = 100, message = "La edad máxima permitida es 100 años")
        int edad

) {

}
