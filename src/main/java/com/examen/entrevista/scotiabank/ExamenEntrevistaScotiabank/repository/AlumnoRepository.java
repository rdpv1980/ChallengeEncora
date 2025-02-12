package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.repository;


import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Long> {
}
