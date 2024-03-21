package com.examen.entrevista.scotiabank.service;

import com.examen.entrevista.scotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {
    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Test
    void crearAlumno() {
        Alumno alumno = new Alumno("1", "John", "Doe", "activo", 25);
        when(alumnoRepository.existeId(1)).thenReturn(Mono.just(false));
        when(alumnoRepository.guardar(alumno)).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> result = alumnoService.crearAlumno(alumno);

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.CREATED)
                .verifyComplete();

        verify(alumnoRepository, times(1)).existeId(1);
        verify(alumnoRepository, times(1)).guardar(alumno);
    }


    @Test
    void obtenerAlumnosActivos() {
        Alumno alumno1 = new Alumno("1", "John", "Doe", "activo", 25);
        Alumno alumno2 = new Alumno("2", "Jane", "Smith", "activo", 28);
        when(alumnoRepository.obtenerAlumnosActivos()).thenReturn(Flux.just(alumno1, alumno2));

        Flux<Alumno> result = alumnoService.obtenerAlumnosActivos();

        StepVerifier.create(result)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();

        verify(alumnoRepository, times(1)).obtenerAlumnosActivos();
    }

    @Test
    void crearAlumnoDuplicateIdError() {
        Alumno alumno = new Alumno("1", "John", "Doe", "activo", 25);
        when(alumnoRepository.existeId(1)).thenReturn(Mono.just(true));

        Mono<ResponseEntity<Void>> result = alumnoService.crearAlumno(alumno);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(alumnoRepository, times(1)).existeId(1);
        verify(alumnoRepository, never()).guardar(alumno);
    }
}
