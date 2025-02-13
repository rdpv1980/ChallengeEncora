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

/**
 * Controlador que maneja las operaciones relacionadas con los alumnos.
 * Expone los endpoints para crear, obtener, actualizar y eliminar alumnos.
 * Este controlador utiliza un enfoque reactivo con Spring WebFlux.
 */

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;
    private final MessageService messageService;

    /**
     * Constructor de la clase AlumnoController.
     * Inyecta los servicios necesarios para gestionar las operaciones de alumnos y mensajes.
     *
     * @param alumnoService El servicio encargado de la lógica de negocio relacionada con los alumnos.
     * @param messageService El servicio encargado de gestionar los mensajes.
     */
    @Autowired
    public AlumnoController(AlumnoService alumnoService, MessageService messageService) {
        this.alumnoService = alumnoService;
        this.messageService = messageService;
    }

    /**
     * Crea un nuevo alumno en el sistema.
     * Recibe un objeto AlumnoDTO con los datos del alumno y devuelve un mensaje de éxito.
     *
     * @param alumnoDTO El objeto que contiene los datos del alumno a crear.
     * @return Una respuesta con el estado HTTP 201 (CREATED) y un mensaje de éxito.
     */
    @PostMapping("/crear")
    public Mono<ResponseEntity<Map<String,String>>> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO)
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of("mensaje", messageService.getAlumnoCreadoMessage())));
    }

    /**
     * Obtiene una lista de todos los alumnos activos.     *
     * @return Un Flux con los datos de todos los alumnos activos.
     */
    @GetMapping("/activos")
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoService.obtenerAlumnosActivos();
    }

    /**
     * Obtiene una lista de todos los alumnos.     *
     * @return Un Flux con los datos de todos los alumnos.
     */
    @GetMapping
    public Flux<AlumnoDTO> obtenerTodos() {
        return alumnoService.obtenerTodos();
    }

    /**
     * Obtiene los detalles de un alumno específico mediante su ID.     *
     * @param id El ID del alumno que se va a obtener.
     * @return Una respuesta con el estado HTTP 200 (OK) y los detalles del alumno si se encuentra, o HTTP 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AlumnoDTO>> obtenerPorId(@PathVariable Long id) {
        return alumnoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza los datos de un alumno existente.
     *
     * @param id El ID del alumno que se va a actualizar.
     * @param alumnoDTO El objeto con los nuevos datos del alumno.
     * @return Una respuesta con el estado HTTP 200 (OK) y los nuevos detalles del alumno si se encuentra, o HTTP 404 si no se encuentra.
     */    @PutMapping("/{id}")
    public Mono<ResponseEntity<AlumnoDTO>> actualizarAlumno(@PathVariable Long id, @Valid @RequestBody AlumnoDTO alumnoDTO) {
        return alumnoService.actualizar(id, alumnoDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un alumno del sistema.
     *
     * @param id El ID del alumno que se va a eliminar.
     * @return Una respuesta con el estado HTTP 204 (No Content) si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarAlumno(@PathVariable Long id) {
        return alumnoService.eliminar(id)
                .then(Mono.just(ResponseEntity.<Void>noContent().build()));
    }

}
