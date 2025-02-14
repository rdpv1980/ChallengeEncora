package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class AlumnoRouter2 {

    @Bean
    public RouterFunction<ServerResponse> route(AlumnoHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/router/alumnos/activos"), handler::obtenerAlumnosActivos)
                .andRoute(RequestPredicates.GET("/router/alumnos"), handler::obtenerTodos)
                .andRoute(RequestPredicates.GET("/router/alumnos/{id}"), handler::obtenerPorId)
                .andRoute(RequestPredicates.POST("/router/alumnos/crear"), handler::crear)
                .andRoute(RequestPredicates.PUT("/router/alumnos/{id}"), handler::actualizar)
                .andRoute(RequestPredicates.DELETE("/router/alumnos/{id}"), handler::eliminar);
    }

}
