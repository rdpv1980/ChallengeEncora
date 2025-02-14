package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.router;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.AlumnoService;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.MessageService;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.validation.AlumnoValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AlumnoRouter {
/*
    @Bean
    public RouterFunction<ServerResponse> alumnoRoutes(AlumnoService alumnoService, MessageService messageService,
                                                        AlumnoValidator alumnoValidator) {
        return RouterFunctions.route()
                .POST("router/alumnos/crear", request ->
                        request.bodyToMono(AlumnoDTO.class)
                                .flatMap(alumno -> {
                                    Errors errors = new BeanPropertyBindingResult(alumno, "alumnoDTO");
                                    alumnoValidator.validate(alumno, errors);

                                    if (errors.hasErrors()) {
                                        Map<String, String> errorMessages = errors.getFieldErrors().stream()
                                                .collect(Collectors.toMap(
                                                        FieldError::getField,
                                                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Error desconocido"
                                                ));

                                        return ServerResponse.badRequest().bodyValue(errorMessages);
                                    }

                                    return alumnoService.crearAlumno(alumno)
                                            .then(ServerResponse.status(HttpStatus.CREATED)
                                                    .bodyValue(Map.of("mensaje", messageService.getAlumnoCreadoMessage())));
                                })
                )
                .GET("router/alumnos/activos", request ->
                        ServerResponse.ok().body(alumnoService.obtenerAlumnosActivos(), AlumnoDTO.class)
                )
                .GET("router/alumnos", request ->
                        ServerResponse.ok().body(alumnoService.obtenerTodos(), AlumnoDTO.class)
                )
                .GET("router/alumnos/{id}", request ->
                        alumnoService.obtenerPorId(Long.parseLong(request.pathVariable("id")))
                                .flatMap(alumno -> ServerResponse.ok().bodyValue(alumno))
                                .switchIfEmpty(ServerResponse.notFound().build())
                )
                .PUT("router/alumnos/{id}", request ->
                        request.bodyToMono(AlumnoDTO.class)
                                .flatMap(alumno -> alumnoService.actualizar(Long.parseLong(request.pathVariable("id")), alumno))
                                .flatMap(updatedAlumno -> ServerResponse.ok().bodyValue(updatedAlumno))
                                .switchIfEmpty(ServerResponse.notFound().build())
                )
                .DELETE("router/alumnos/{id}", request ->
                        alumnoService.eliminar(Long.parseLong(request.pathVariable("id")))
                                .then(ServerResponse.noContent().build())
                )
                .build();
    }

 */
}
