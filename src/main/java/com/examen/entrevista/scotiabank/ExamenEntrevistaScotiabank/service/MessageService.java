package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${error.estado.invalido}")
    private String estadoInvalido;

    @Value("${error.alumno.id.duplicado}")
    private String alumnoIdDuplicado;

    public String getEstadoInvalidoMessage() {
        return estadoInvalido;
    }

    public String getAlumnoIdDuplicadoMessage() {
        return alumnoIdDuplicado;
    }

    @Getter
    @Value("${mensaje.alumno.creado}")
    private String alumnoCreadoMessage;


}
