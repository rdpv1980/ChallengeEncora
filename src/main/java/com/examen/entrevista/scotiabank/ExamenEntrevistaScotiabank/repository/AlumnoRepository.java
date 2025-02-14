package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;


import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository{
    Mono<Boolean> existeId(Long id);
    Mono<Alumno> guardar(Alumno alumno);
    Flux<Alumno> obtenerAlumnosActivos();
    Flux<Alumno> obtenerTodos();
    Mono<Alumno> obtenerPorId(Long id);
    public Mono<Void> eliminar(Long id);
    public Mono<Alumno> actualizar(Alumno alumno);
}
