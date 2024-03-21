package com.examen.entrevista.scotiabank.repository;

import com.examen.entrevista.scotiabank.model.Alumno;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository{
    private final Map<Integer, Alumno> almacenamiento = new HashMap<>();

    @Override
    public Mono<Alumno> guardar(Alumno alumno) {
        almacenamiento.put(Integer.parseInt(alumno.getId()), alumno);
        return Mono.just(alumno);
    }

    @Override
    public Mono<Boolean> existeId(Integer id) {
        return Mono.just(almacenamiento.containsKey(id));
    }

    @Override
    public Flux<Alumno> obtenerAlumnosActivos() {
        return Flux.fromIterable(almacenamiento.values())
                .filter(alumno -> alumno.getEstado().equalsIgnoreCase("ACTIVO"));
    }

}
