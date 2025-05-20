package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import jakarta.validation.ConstraintViolation;

import static org.junit.jupiter.api.Assertions.*;

class AeropuertoResponseUnitTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAeropuertoResponse() {
        // Given
        AeropuertoResponse aeropuerto = new AeropuertoResponse();
        aeropuerto.setCodigoAeropuerto("MAD");
        aeropuerto.setPais("ES");

        // When
        Set<ConstraintViolation<AeropuertoResponse>> violations = validator.validate(aeropuerto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testBlankCodigoAeropuerto() {
        // Given
        AeropuertoResponse aeropuerto = new AeropuertoResponse();
        aeropuerto.setCodigoAeropuerto(" ");
        aeropuerto.setPais("ES");

        // When
        Set<ConstraintViolation<AeropuertoResponse>> violations = validator.validate(aeropuerto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("no debe estar vacío", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullPais() {
        // Given
        AeropuertoResponse aeropuerto = new AeropuertoResponse();
        aeropuerto.setCodigoAeropuerto("MAD");
        aeropuerto.setPais(null);

        // When
        Set<ConstraintViolation<AeropuertoResponse>> violations = validator.validate(aeropuerto);

        // Then
        assertEquals(1, violations.size());
    }
}
