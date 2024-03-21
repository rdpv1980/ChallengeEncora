package com.examen.entrevista.scotiabank.controller;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<String>> crearAlumno(@RequestBody @Valid Alumno alumno) {
       // return alumnoService.crearAlumno(alumno);
      if(alumno.getId()==null || alumno.getId().isBlank()){
            return Mono.just(ResponseEntity.badRequest().body("El id del alumno no debe ser nulo o vacio"));
        }

       if(alumno.getNombre()==null || alumno.getNombre().isBlank()){
            return Mono.just(ResponseEntity.badRequest().body("El nombre del alumno no debe ser nulo o vacio"));
        }

        if(alumno.getApellido()==null || alumno.getApellido().isBlank()){
            return Mono.just(ResponseEntity.badRequest().body("El apellido del alumno no debe ser nulo o vacio"));
        }

        if(alumno.getEstado()==null || alumno.getEstado().isBlank()){
            return Mono.just(ResponseEntity.badRequest().body("El estado del alumno no debe ser nulo o vacio"));
        }
        if(alumno.getEdad()==null || alumno.getEdad()<=0){
            return Mono.just(ResponseEntity.badRequest().body("La edad del alumno no debe ser nulo o debe ser mayor a cero"));
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
