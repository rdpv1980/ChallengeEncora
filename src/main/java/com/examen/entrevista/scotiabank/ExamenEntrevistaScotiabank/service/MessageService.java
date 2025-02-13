package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {


    @Getter
    @Value("${error.estado.invalido}")
    private String estadoInvalido;

    @Getter
    @Value("${mensaje.alumno.creado}")
    private String alumnoCreadoMessage;


}
