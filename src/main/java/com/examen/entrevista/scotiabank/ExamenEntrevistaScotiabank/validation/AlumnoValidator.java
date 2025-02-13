package com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.validation;

import com.examen.entrevista.scotiabank.ExamenEntrevistaScotiabank.model.dto.AlumnoDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AlumnoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AlumnoDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AlumnoDTO alumno = (AlumnoDTO) target;

        // Validar nombre y apellido no vacíos
        if (alumno.nombre() == null || alumno.nombre().trim().isEmpty()) {
            errors.rejectValue("nombre", "campo.vacio", "El nombre no puede estar vacío");
        }
        if (alumno.apellido() == null || alumno.apellido().trim().isEmpty()) {
            errors.rejectValue("apellido", "campo.vacio", "El apellido no puede estar vacío");
        }

        // Validar edad entre 12 y 100 años
        if (alumno.edad() < 12 || alumno.edad() > 100) {
            errors.rejectValue("edad", "rango.invalido", "La edad debe estar entre 12 y 100 años");
        }
    }
}
