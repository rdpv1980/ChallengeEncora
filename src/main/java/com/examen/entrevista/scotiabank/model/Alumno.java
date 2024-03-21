package com.examen.entrevista.scotiabank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {

  //  @NotNull(message = "no puede ser nulo")
  //  @NotBlank(message = "no puede estar en blanco")
    private String id;

   // @NotNull
   // @NotBlank
    private String nombre;

    //@NotNull
    //@NotBlank
    private String apellido;

    //@NotNull
    //@NotBlank
    private String estado;

    //@NotNull
   // @NotBlank
    //@Pattern(regexp = "\\d+", message = "La edad debe ser un número")
    private Integer edad;

}
