package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.router;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.AlumnoService;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.MessageService;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.validation.AlumnoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AlumnoHandler {

    private final AlumnoService alumnoService;
    private final MessageService messageService;
    private final AlumnoValidator alumnoValidator;

    public AlumnoHandler(AlumnoService alumnoService, MessageService messageService, AlumnoValidator alumnoValidator) {
        this.alumnoService = alumnoService;
        this.messageService = messageService;
        this.alumnoValidator = alumnoValidator;
    }

    public Mono<ServerResponse> crear(ServerRequest request) {
        return request.bodyToMono(AlumnoDTO.class)
                .flatMap(alumno -> {
                    Errors errors = new BeanPropertyBindingResult(alumno, "alumnoDTO");
                    alumnoValidator.validate(alumno, errors);

                    if (errors.hasErrors()) {
                        Map<String, String> errorMessages = errors.getFieldErrors().stream()
                                .collect(Collectors.toMap(
                                        FieldError::getField,
                                        FieldError::getDefaultMessage
                                ));
                        return ServerResponse.badRequest().bodyValue(errorMessages);
                    }

                    return alumnoService.crearAlumno(alumno)
                            .then(ServerResponse.status(HttpStatus.CREATED)
                                    .bodyValue(Map.of("mensaje", messageService.getAlumnoCreadoMessage())));
                });
    }

    public Mono<ServerResponse> obtenerAlumnosActivos(ServerRequest request) {
        return ServerResponse.ok().body(alumnoService.obtenerAlumnosActivos(), AlumnoDTO.class);
    }

    public Mono<ServerResponse> obtenerTodos(ServerRequest request) {
        return ServerResponse.ok().body(alumnoService.obtenerTodos(), AlumnoDTO.class);
    }

    public Mono<ServerResponse> obtenerPorId(ServerRequest request) {
        return alumnoService.obtenerPorId(Long.parseLong(request.pathVariable("id")))
                .flatMap(alumno -> ServerResponse.ok().bodyValue(alumno))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> actualizar(ServerRequest request) {
        return request.bodyToMono(AlumnoDTO.class)
                .flatMap(alumno -> alumnoService.actualizar(Long.parseLong(request.pathVariable("id")), alumno))
                .flatMap(updatedAlumno -> ServerResponse.ok().bodyValue(updatedAlumno))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> eliminar(ServerRequest request) {
        return alumnoService.eliminar(Long.parseLong(request.pathVariable("id")))
                .then(ServerResponse.noContent().build());
    }
}

