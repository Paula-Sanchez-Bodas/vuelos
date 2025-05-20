package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.OfertaResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VueloResponseUnitTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidVueloResponse() {
        // Given
        VueloResponse vuelo = new VueloResponse();
        List<OfertaResponse> ofertas = new ArrayList<>();
        vuelo.setListaOfertas(ofertas);

        // When
        Set<ConstraintViolation<VueloResponse>> violations = validator.validate(vuelo);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullListaOfertas() {
        // Given
        VueloResponse vuelo = new VueloResponse();
        vuelo.setListaOfertas(null);

        // When
        Set<ConstraintViolation<VueloResponse>> violations = validator.validate(vuelo);

        // Then
        assertEquals(1, violations.size());
    }
}
