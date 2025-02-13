package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository{
    private final ConcurrentHashMap<Long, Alumno> almacenamiento = new ConcurrentHashMap<>();
    private final AtomicLong contadorId = new AtomicLong(1); // 🔥 Generador de ID automático


    @Override
    public Mono<Alumno> guardar(Alumno alumno) {
        alumno.setId(contadorId.getAndIncrement());
        almacenamiento.put(alumno.getId(), alumno);
        return Mono.just(alumno);
    }

    @Override
    public Mono<Boolean> existeId(Long id) {
        if (id == null) {
            return Mono.just(false); // 🔥 Si el ID es null, asumimos que no existe
        }
        return Mono.just(almacenamiento.containsKey(id));
    }

    @Override
   public Flux<Alumno> obtenerAlumnosActivos() {
        return Flux.fromIterable(almacenamiento.values());
    }

    @Override
    public Flux<Alumno> obtenerTodos() {
        return Flux.fromIterable(almacenamiento.values());
    }

    @Override
    public Mono<Alumno> obtenerPorId(Long id) {
        return Mono.justOrEmpty(almacenamiento.get(id));
    }

    @Override
    public Mono<Void> eliminar(Long id) {
        almacenamiento.remove(id);
        return Mono.empty();
    }

    @Override
    public Mono<Alumno> actualizar(Alumno alumno) {
        if (!almacenamiento.containsKey(alumno.getId())) {
            return Mono.empty(); // 🔥 Si el ID no existe, devuelve vacío
        }
        almacenamiento.put(alumno.getId(), alumno);
        return Mono.just(alumno);
    }
}
