package com.examen.entrevista.scotiabank.service;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.repository.AlumnoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepositoryImpl alumnoRepositoryImpl;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepositoryImpl alumnoRepositoryImpl) {
        this.alumnoRepositoryImpl = alumnoRepositoryImpl;
    }

    @Override
    public Mono<ResponseEntity<Void>> crearAlumno(Alumno alumno) {
        return alumnoRepositoryImpl.existeId(Integer.parseInt(alumno.getId()))
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new RuntimeException("El ID del alumno ya existe"));
                    } else {
                        return alumnoRepositoryImpl.guardar(alumno)
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
                    }
                });
    }

    @Override
    public Flux<Alumno> obtenerAlumnosActivos() {
        return alumnoRepositoryImpl.obtenerAlumnosActivos();
    }
}
