package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;


import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Mono<ResponseEntity<Void>> crearAlumno(AlumnoDTO alumnoDTO);
    Flux<AlumnoDTO> obtenerAlumnosActivos();
    Flux<AlumnoDTO> obtenerTodos();
    Mono<AlumnoDTO> obtenerPorId(Long id);
    Mono<AlumnoDTO> actualizar(Long id, AlumnoDTO alumnoDTO);
    Mono<Void> eliminar(Long id);
}
