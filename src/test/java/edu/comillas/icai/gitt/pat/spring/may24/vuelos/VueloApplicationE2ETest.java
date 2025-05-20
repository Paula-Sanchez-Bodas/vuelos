package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VuelosApplicationE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testBuscarAeropuertosE2E() {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // When
        ResponseEntity<AeropuertoResponse[]> response = restTemplate.exchange(
                "/aeropuertos?nombreCiudad=Madrid",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                AeropuertoResponse[].class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertNotNull(response.getBody());
    }
    /*
    @Test
    void testCrearReservaE2E() {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"vueloId\":\"123\",\"pasajero\":\"John Doe\"}";

        // When
        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:8080/reservas",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                Void.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }*/
}
