package com.examen.entrevista.scotiabank.service;

import com.examen.entrevista.scotiabank.model.Alumno;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AlumnoService {
    Mono<ResponseEntity<Void>> crearAlumno(Alumno alumno);
    Flux<Alumno> obtenerAlumnosActivos();
}
