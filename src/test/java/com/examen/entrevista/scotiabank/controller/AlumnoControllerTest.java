package com.examen.entrevista.scotiabank.controller;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.model.Estado;
import com.examen.entrevista.scotiabank.service.AlumnoService;
import com.examen.entrevista.scotiabank.service.AlumnoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.*;

@WebFluxTest(controllers = AlumnoController.class)
public class AlumnoControllerTest {
    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @Test
    void obtenerAlumnosActivos() {
        Alumno alumno1 = new Alumno("1", "John", "Doe", "activo", 25);
        Alumno alumno2 = new Alumno("2", "Jane", "Smith", "activo", 28);
        when(alumnoService.obtenerAlumnosActivos()).thenReturn(Flux.just(alumno1, alumno2));

        Flux<Alumno> result = alumnoController.obtenerAlumnosActivos();

        StepVerifier.create(result)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();

        verify(alumnoService, times(1)).obtenerAlumnosActivos();
    }

}
