package com.examen.entrevista.scotiabank.service;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.model.Estado;
import com.examen.entrevista.scotiabank.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AlumnoService {
    Mono<ResponseEntity<Void>> crearAlumno(Alumno alumno);
    Flux<Alumno> obtenerAlumnosActivos();
}
