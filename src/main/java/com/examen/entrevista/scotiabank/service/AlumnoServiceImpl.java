package com.examen.entrevista.scotiabank.service;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Mono<ResponseEntity<Void>> crearAlumno(Alumno alumno) {
        return alumnoRepository.existeId(Integer.parseInt(alumno.getId()))
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new RuntimeException("El ID del alumno ya existe"));//Mono.just(ResponseEntity.badRequest().build());
                    } else {
                        return alumnoRepository.guardar(alumno)
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
                    }
                });
    }

    @Override
    public Flux<Alumno> obtenerAlumnosActivos() {
        return alumnoRepository.obtenerAlumnosActivos();
    }
}
