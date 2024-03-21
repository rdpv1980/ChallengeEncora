package com.examen.entrevista.scotiabank;

import com.examen.entrevista.scotiabank.model.Alumno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class Util {

    public Mono<ResponseEntity<String>> validarAlumno(@RequestBody Alumno alumno) {
        // return alumnoService.crearAlumno(alumno);
        if (alumno.getId() == null || alumno.getId().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().body("El id del alumno no debe ser nulo o vacio"));
        }

        if (alumno.getNombre() == null || alumno.getNombre().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().body("El nombre del alumno no debe ser nulo o vacio"));
        }
        return Mono.just(ResponseEntity.ok().body("datos correctos"));
    }
}
