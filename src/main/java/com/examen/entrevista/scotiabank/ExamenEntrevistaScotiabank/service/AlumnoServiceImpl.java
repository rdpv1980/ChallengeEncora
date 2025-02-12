package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.advice.AlumnoYaExisteException;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepositoryImpl alumnoRepositoryImpl;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepositoryImpl alumnoRepositoryImpl) {
        this.alumnoRepositoryImpl = alumnoRepositoryImpl;
    }

    @Override
    public Mono<ResponseEntity<Void>> crearAlumno(AlumnoDTO alumnoDTO) {

        Alumno alumno = new Alumno(alumnoDTO.id(), alumnoDTO.nombre(), alumnoDTO.apellido(),
                                  alumnoDTO.estado(), alumnoDTO.edad());
        return alumnoRepositoryImpl.existeId(alumno.getId())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new AlumnoYaExisteException("El ID del alumno ya existe"));
                    } else {
                        return alumnoRepositoryImpl.guardar(alumno)
                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
                    }
                });
    }

    @Override
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoRepositoryImpl.obtenerAlumnosActivos()
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }
}
