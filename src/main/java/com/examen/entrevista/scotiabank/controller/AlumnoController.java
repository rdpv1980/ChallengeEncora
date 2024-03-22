package com.examen.entrevista.scotiabank.controller;

import com.examen.entrevista.scotiabank.Util;
import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.service.AlumnoService;
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
    public Mono<ResponseEntity<String>> crearAlumno(@RequestBody /*@Valid*/ Alumno alumno) {

        if(Util.validarAlumno(alumno)!=null){
            return Mono.just(ResponseEntity.badRequest().body(Util.validarAlumno(alumno)));
        }

        return alumnoService.crearAlumno(alumno)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Alumno guardado exitosamente"))
                .onErrorResume(e -> {

                    if (e instanceof RuntimeException && e.getMessage().equals("El ID del alumno ya existe")) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("No se puede grabar, ID duplicado"));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error interno del servidor"));
                });

    }

    @GetMapping("/activos")
    public Flux<Alumno> obtenerAlumnosActivos() {
        return alumnoService.obtenerAlumnosActivos();
    }

}
