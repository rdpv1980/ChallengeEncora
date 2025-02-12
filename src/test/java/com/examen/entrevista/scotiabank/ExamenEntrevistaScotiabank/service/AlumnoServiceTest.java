package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepository;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {
    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Mock
    private AlumnoRepositoryImpl alumnoRepository;

    private Alumno alumno;
    private AlumnoDTO alumnoDTO;
    private List<Alumno> alumnos;

    @BeforeEach
    void setUp() {
        alumno = new Alumno(1L, "Ronald", "Fernandez", Estado.ACTIVO, 18);
        Alumno alumno2 = new Alumno(2L, "Carlos", "González", Estado.INACTIVO, 22);
        alumnos=List.of(alumno,alumno2);

        alumnoDTO = new AlumnoDTO(null, "Ronald", "Fernandez", Estado.ACTIVO, 18);
    }

    @Test
    void obtenerTodos_DeberiaRetornarCantidadCorrectaDeAlumnos() {

        when(alumnoRepository.obtenerTodos()).thenReturn(Flux.fromIterable(alumnos)); // 🔥 Simulamos varios alumnos

        StepVerifier.create(alumnoService.obtenerTodos())
                .expectNextCount(2) // 🔥 Verifica que haya exactamente 2 alumnos
                .verifyComplete();

        verify(alumnoRepository).obtenerTodos();
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarAlumno() {
        when(alumnoRepository.obtenerPorId(1L)).thenReturn(Mono.just(alumno));

        StepVerifier.create(alumnoService.obtenerPorId(1L))
                .expectNextMatches(a -> a.id().equals(1L) && a.nombre().equals("Ronald"))
                .verifyComplete();

        verify(alumnoRepository).obtenerPorId(1L);
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarVacio() {
        when(alumnoRepository.obtenerPorId(99L)).thenReturn(Mono.empty());

        StepVerifier.create(alumnoService.obtenerPorId(99L))
                .verifyComplete();

        verify(alumnoRepository).obtenerPorId(99L);
    }

    @Test
    void actualizar_CuandoExiste_DeberiaActualizarAlumno() {
        when(alumnoRepository.obtenerPorId(1L)).thenReturn(Mono.just(alumno));
        when(alumnoRepository.actualizar(any(Alumno.class))).thenReturn(Mono.just(alumno));

        StepVerifier.create(alumnoService.actualizar(1L, alumnoDTO))
                .expectNextMatches(a -> a.id().equals(1L) && a.nombre().equals("Ronald"))
                .verifyComplete();

        verify(alumnoRepository).obtenerPorId(1L);
        verify(alumnoRepository).actualizar(any(Alumno.class));
    }

    @Test
    void eliminar_CuandoExiste_DeberiaEliminarAlumno() {
        when(alumnoRepository.eliminar(1L)).thenReturn(Mono.empty());

        StepVerifier.create(alumnoService.eliminar(1L))
                .verifyComplete();

        verify(alumnoRepository).eliminar(1L);
    }

}