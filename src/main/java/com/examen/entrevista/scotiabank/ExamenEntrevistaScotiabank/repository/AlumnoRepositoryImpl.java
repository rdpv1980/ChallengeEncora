package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository{
    private final Map<Long, Alumno> almacenamiento = new HashMap<>();

    @Override
    public Mono<Alumno> guardar(Alumno alumno) {
        almacenamiento.put(alumno.getId(), alumno);
        return Mono.just(alumno);
    }

    @Override
    public Mono<Boolean> existeId(Long id) {
        return Mono.just(almacenamiento.containsKey(id));
    }

    @Override
   public Flux<Alumno> obtenerAlumnosActivos() {
        return Flux.fromIterable(almacenamiento.values())
                .filter(alumno -> alumno.getEstado()== Estado.ACTIVO);
    }
}
