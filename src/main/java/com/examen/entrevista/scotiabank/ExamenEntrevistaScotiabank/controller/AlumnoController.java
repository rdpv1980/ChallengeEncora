package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.controller;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.AlumnoService;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;
    private final MessageService messageService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService, MessageService messageService) {
        this.alumnoService = alumnoService;
        this.messageService = messageService;
    }

    @PostMapping("/crear")
    public Mono<ResponseEntity<Map<String,String>>> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of("mensaje", messageService.getAlumnoCreadoMessage())));
    }

    @GetMapping("/activos")
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoService.obtenerAlumnosActivos();
    }

    @GetMapping
    public Flux<AlumnoDTO> obtenerTodos() {
        return alumnoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AlumnoDTO>> obtenerPorId(@PathVariable Long id) {
        return alumnoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AlumnoDTO>> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.actualizar(id, alumnoDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarAlumno(@PathVariable Long id) {
        return alumnoService.eliminar(id)
                .then(Mono.just(ResponseEntity.<Void>noContent().build()));
    }

}
