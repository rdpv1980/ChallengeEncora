package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.controller;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.AlumnoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<String>> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Alumno guardado exitosamente"));
    }

    @GetMapping("/activos")
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoService.obtenerAlumnosActivos();
    }

}
