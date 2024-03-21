package com.examen.entrevista.scotiabank;

import com.examen.entrevista.scotiabank.model.Alumno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class Util {

    public static String validarAlumno(Alumno alumno) {
        if (alumno.getId() == null || alumno.getId().isBlank()) {
            return "El id del alumno no debe ser nulo o vacío";
        }
        if (alumno.getNombre() == null || alumno.getNombre().isBlank()) {
            return "El nombre del alumno no debe ser nulo o vacío";
        }
        if (alumno.getApellido() == null || alumno.getApellido().isBlank()) {
            return "El apellido del alumno no debe ser nulo o vacío";
        }
        if (alumno.getEstado() == null || alumno.getEstado().isBlank()) {
            return "El estado del alumno no debe ser nulo o vacío";
        }
        if (alumno.getEdad() == null || alumno.getEdad() <= 0) {
            return "La edad del alumno no debe ser nula o debe ser mayor a cero";
        }
        return null; // Significa que la validación fue exitosa
    }
}
