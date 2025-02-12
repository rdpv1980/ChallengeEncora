package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("alumno") // Indica que esta clase representa la tabla 'alumno'
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

    @Id
    @Column("id") // Mapea la columna 'id' de la tabla
    private Long id;

    @Column("nombre")
    private String nombre;

    @Column("apellido")
    private String apellido;

    @Column("estado")
    private Estado estado;

    @Column("edad")
    private int edad;
}
