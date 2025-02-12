package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;


import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoRepository{
    Mono<Boolean> existeId(Long id);
    Mono<Alumno> guardar(Alumno alumno);
    Flux<Alumno> obtenerAlumnosActivos();
}
