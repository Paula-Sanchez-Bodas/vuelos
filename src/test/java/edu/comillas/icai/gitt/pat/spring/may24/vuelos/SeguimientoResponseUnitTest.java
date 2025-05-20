package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.SeguimientoResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SeguimientoResponseUnitTest {
    private static Validator validator;
    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Test
    void testBlankOrigen() {
        SeguimientoResponse seguimiento = new SeguimientoResponse();
        seguimiento.setOrigen(" ");

        Set<ConstraintViolation<SeguimientoResponse>> violations = validator.validate(seguimiento);
        assertFalse(violations.isEmpty());
    }
}
