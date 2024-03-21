package com.examen.entrevista.scotiabank.repository;

import com.examen.entrevista.scotiabank.model.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AlumnoRepository {
    private final Map<Integer, Alumno> almacenamiento = new HashMap<>();

    public Mono<Alumno> guardar(Alumno alumno) {
        almacenamiento.put(Integer.parseInt(alumno.getId()), alumno);
        return Mono.just(alumno);
    }

    public Flux<Alumno> obtenerAlumnos() {
        return Flux.fromIterable(almacenamiento.values());
    }

    public Mono<Boolean> existeId(int id) {
        return Mono.just(almacenamiento.containsKey(id));
    }

    public Flux<Alumno> obtenerAlumnosActivos() {
        return Flux.fromIterable(almacenamiento.values())
                .filter(alumno -> alumno.getEstado().equalsIgnoreCase("ACTIVO"));
    }

}
