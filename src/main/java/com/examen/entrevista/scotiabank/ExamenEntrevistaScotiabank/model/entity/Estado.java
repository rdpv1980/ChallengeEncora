package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity;

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
        throw new IllegalArgumentException("El estado debe ser ACTIVO o INACTIVO");
    }

   @JsonValue
    public String toJson() {
        return name(); // Siempre devuelve ACTIVO o INACTIVO en mayúsculas
    }
}
