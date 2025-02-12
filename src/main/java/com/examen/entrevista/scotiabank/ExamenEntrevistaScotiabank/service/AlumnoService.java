package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;


import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AlumnoService {
    Mono<ResponseEntity<Void>> crearAlumno(AlumnoDTO alumnoDTO);
    Flux<AlumnoDTO> obtenerAlumnosActivos();
}
