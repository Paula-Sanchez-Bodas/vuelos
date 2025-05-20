package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.OfertaResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OfertaResponseUnitTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidOfertaResponse() {
        OfertaResponse oferta = new OfertaResponse();
        oferta.setPrecio(150.0);
        // Configurar itinerarios...

        Set<ConstraintViolation<OfertaResponse>> violations = validator.validate(oferta);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNegativePrice() {
        OfertaResponse oferta = new OfertaResponse();
        oferta.setPrecio(-100.0);

        Set<ConstraintViolation<OfertaResponse>> violations = validator.validate(oferta);
        assertEquals(1, violations.size());
    }
}
