package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Estado;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Mono<ResponseEntity<Void>> crearAlumno(AlumnoDTO alumnoDTO) {

        Alumno alumno = new Alumno(null, alumnoDTO.nombre(), alumnoDTO.apellido(),
                                  alumnoDTO.estado(), alumnoDTO.edad());

        return alumnoRepository.guardar(alumno)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoRepository.obtenerAlumnosActivos()
                .filter(alumno -> alumno.getEstado()== Estado.ACTIVO)
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }

    @Override
    public Flux<AlumnoDTO> obtenerTodos() {
        return alumnoRepository.obtenerTodos()
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }

    @Override
    public Mono<AlumnoDTO> obtenerPorId(Long id) {
        return alumnoRepository.obtenerPorId(id)
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }

    @Override
    public Mono<AlumnoDTO> actualizar(Long id, AlumnoDTO alumnoDTO) {
        return alumnoRepository.obtenerPorId(id)
                .flatMap(alumnoExistente -> {
                    alumnoExistente.setNombre(alumnoDTO.nombre());
                    alumnoExistente.setApellido(alumnoDTO.apellido());
                    alumnoExistente.setEstado(alumnoDTO.estado());
                    alumnoExistente.setEdad(alumnoDTO.edad());
                    return alumnoRepository.actualizar(alumnoExistente);
                })
                .map(alumnoActualizado -> new AlumnoDTO(
                        alumnoActualizado.getId(),
                        alumnoActualizado.getNombre(),
                        alumnoActualizado.getApellido(),
                        alumnoActualizado.getEstado(),
                        alumnoActualizado.getEdad()
                ));
    }

    @Override
    public Mono<Void> eliminar(Long id) {
        return alumnoRepository.eliminar(id);
    }
}
