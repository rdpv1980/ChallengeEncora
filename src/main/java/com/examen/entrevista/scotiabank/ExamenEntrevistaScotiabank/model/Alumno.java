package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno {

    private Long id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;

}
