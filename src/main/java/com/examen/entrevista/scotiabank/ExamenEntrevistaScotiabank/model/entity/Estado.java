package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Estado {
    ACTIVO,
    INACTIVO;

    @JsonCreator
    public static Estado fromString(String value) {
        System.out.println("Valor recibido en fromString(): [" + value + "]"); // 🔍 Debug

        for (Estado estado : Estado.values()) {
            System.out.println("Comparando con: [" + estado.name() + "]"); // 🔍 Debug
            if (estado.name().equalsIgnoreCase(value.trim())) { // 🔥 Agregamos trim()
                System.out.println("llego a este if");
                return estado;
            }
            System.out.println("no entro al if");
        }
        System.out.println("salio del for");
        throw new IllegalArgumentException();
    }

   //@JsonValue
   // public String toJson() {
      //  return name(); // Siempre devuelve ACTIVO o INACTIVO en mayúsculas
    //}
}
