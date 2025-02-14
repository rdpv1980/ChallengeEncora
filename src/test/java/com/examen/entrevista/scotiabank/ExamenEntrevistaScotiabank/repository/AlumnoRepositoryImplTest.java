package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AlumnoRepositoryImplTest {

    private AlumnoRepositoryImpl alumnoRepository;

    private Alumno alumno1, alumno2;

    @BeforeEach
    void setUp() {
        alumnoRepository = new AlumnoRepositoryImpl();

        alumno1 = new Alumno(1L, "Ronald", "Fernandez", Estado.ACTIVO, 18);
        alumno2 = new Alumno(99L, "Carlos", "González", Estado.INACTIVO, 22);
    }

    @Test
    void guardar_DeberiaAsignarIdYAlmacenarAlumno() {
        StepVerifier.create(alumnoRepository.guardar(alumno1))
                .assertNext(alumno -> {
                    assertNotNull(alumno.getId()); // ✅ ID debe generarse automáticamente
                    assertEquals("Ronald", alumno.getNombre());
                })
                .verifyComplete();
    }

    @Test
    void obtenerTodos_DeberiaRetornarTodosLosAlumnos() {
        alumnoRepository.guardar(alumno1);
        alumnoRepository.guardar(alumno2);

        StepVerifier.create(alumnoRepository.obtenerTodos().collectList()) // 🔥 Convertimos Flux a List
                .assertNext(alumnos -> assertEquals(2, alumnos.size())) // ✅ Verificamos tamaño exacto
                .verifyComplete();
    }
    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarAlumno() {
        //block fuerza a que la operación reactiva se ejecute de manera sincrónica y devuelve el resultado inmediatamente.

        Alumno guardado = alumnoRepository.guardar(alumno1).block();
        StepVerifier.create(alumnoRepository.obtenerPorId(guardado.getId()))
                .assertNext(alumno -> assertEquals("Ronald", alumno.getNombre()))
                .verifyComplete();
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaRetornarVacio() {
        StepVerifier.create(alumnoRepository.obtenerPorId(3L))
                .verifyComplete(); // ✅ No debe devolver nada
    }

    @Test
    void actualizar_DeberiaModificarAlumnoSinCambiarId() {
        Alumno guardado = alumnoRepository.guardar(alumno1).block();
        guardado.setNombre("Ronald Updated");

        StepVerifier.create(alumnoRepository.actualizar(guardado))
                .assertNext(alumno -> {
                    assertEquals("Ronald Updated", alumno.getNombre());
                    assertEquals(guardado.getId(), alumno.getId()); // ✅ El ID no cambia
                })
                .verifyComplete();
    }

    @Test
    void eliminar_DeberiaEliminarAlumnoPorId() {
        Alumno guardado = alumnoRepository.guardar(alumno1).block();

        StepVerifier.create(alumnoRepository.eliminar(guardado.getId()))
                .verifyComplete(); // ✅ Se elimina sin errores

        StepVerifier.create(alumnoRepository.obtenerPorId(guardado.getId()))
                .verifyComplete(); // ✅ Ya no debe existir
    }
}
