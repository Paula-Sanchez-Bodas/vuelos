package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.services.VueloServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class VueloServicioImplementacionIntegrationTest {

    @Autowired
    private VueloServicio vueloServicio;

    @Test
    void testGetAccessToken() {
        // When
        String token = vueloServicio.getAccessToken();

        // Then
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testBuscarAeropuertos() {
        // When
        List<AeropuertoResponse> aeropuertos = vueloServicio.buscarAeropuertos("Madrid");

        // Then
        assertNotNull(aeropuertos);
        assertFalse(aeropuertos.isEmpty());
    }

}