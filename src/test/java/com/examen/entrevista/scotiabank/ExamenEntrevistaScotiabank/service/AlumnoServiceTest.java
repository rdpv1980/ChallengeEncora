package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepositoryImpl;
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
    private AlumnoRepositoryImpl alumnoRepositoryImpl;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Test
    void crearAlumno() {
   /*     Alumno alumnoDTO = new Alumno(1L, "Robert", "Inga", "activo", 25);
        //Given
        when(alumnoRepositoryImpl.existeId(1L)).thenReturn(Mono.just(false));
        when(alumnoRepositoryImpl.guardar(alumno)).thenReturn(Mono.empty());

        //When
        Mono<ResponseEntity<Void>> result = alumnoService.crearAlumno(alumnoDTO);

        //Then
        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode() == HttpStatus.CREATED)
                .verifyComplete();

        verify(alumnoRepositoryImpl, times(1)).existeId(1);
        verify(alumnoRepositoryImpl, times(1)).guardar(alumno);*/
    }


    @Test
    void obtenerAlumnosActivos() {
        //Given
  /*      Alumno alumno1 = new Alumno("1", "Robert", "Inga", "activo", 25);
        Alumno alumno2 = new Alumno("2", "Juana", "Salazar", "activo", 28);
        when(alumnoRepositoryImpl.obtenerAlumnosActivos()).thenReturn(Flux.just(alumno1, alumno2));

        //When
        Flux<Alumno> result = alumnoService.obtenerAlumnosActivos();

        //Then
        StepVerifier.create(result)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();

        verify(alumnoRepositoryImpl, times(1)).obtenerAlumnosActivos();*/
    }

    @Test
    void crearAlumnoDuplicateIdError() {
   /*     //Given
        Alumno alumno = new Alumno("1", "Robert", "Inga", "activo", 25);
        when(alumnoRepositoryImpl.existeId(1)).thenReturn(Mono.just(true));

        //When
        Mono<ResponseEntity<Void>> result = alumnoService.crearAlumno(alumno);

        //Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(alumnoRepositoryImpl, times(1)).existeId(1);
        verify(alumnoRepositoryImpl, never()).guardar(alumno);*/
    }
}