package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity.Alumno;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity.Estado;
import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Mono<ResponseEntity<Void>> crearAlumno(AlumnoDTO alumnoDTO) {

//        Alumno alumno = new Alumno(alumnoDTO.id(), alumnoDTO.nombre(), alumnoDTO.apellido(),
//                                  alumnoDTO.estado(), alumnoDTO.edad());
//        return alumnoRepositoryImpl.existeId(alumno.getId())
//                .flatMap(existe -> {
//                    if (existe) {
//                        return Mono.error(new AlumnoYaExisteException("El ID del alumno ya existe"));
//                    } else {
//                        return alumnoRepositoryImpl.guardar(alumno)
//                                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
//                    }
//                });

        Alumno alumno = new Alumno(null, alumnoDTO.nombre(), alumnoDTO.apellido(), alumnoDTO.estado(), alumnoDTO.edad());
        return alumnoRepository.save(alumno)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Flux<AlumnoDTO> obtenerAlumnosActivos() {
        return alumnoRepository.findAll()
                .filter(alumno -> alumno.getEstado()== Estado.ACTIVO)
                .map(alumno -> new AlumnoDTO(
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getEstado(),
                        alumno.getEdad()
                ));
    }
}
