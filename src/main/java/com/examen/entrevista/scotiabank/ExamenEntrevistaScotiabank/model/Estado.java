package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {
    ACTIVO,
    INACTIVO;

    @JsonCreator
    public static Estado fromString(String value) {
        for (Estado estado : Estado.values()) {
            if (estado.name().equalsIgnoreCase(value)) {
                return estado;
            }
        }
        throw new IllegalArgumentException();
    }
}
