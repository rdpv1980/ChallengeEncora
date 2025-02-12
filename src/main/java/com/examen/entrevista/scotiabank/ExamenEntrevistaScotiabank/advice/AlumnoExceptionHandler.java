package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.advice;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.service.MessageService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AlumnoExceptionHandler {

    private final MessageService messageService;

    public AlumnoExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(WebExchangeBindException  ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existing, replacement) -> existing // En caso de duplicados, mantener el primer mensaje
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidEnum(IllegalArgumentException ex) {
        return Map.of("error", messageService.getEstadoInvalidoMessage());
    }
}
