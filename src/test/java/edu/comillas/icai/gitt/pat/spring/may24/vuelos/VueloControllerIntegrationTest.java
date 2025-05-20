package edu.comillas.icai.gitt.pat.spring.may24.vuelos;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.controllers.VueloController;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.services.VueloServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VueloController.class)
class VueloControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VueloServicio vueloServicio;

    @Test
    void buscarVuelosOk() throws Exception {
        // Given
        VueloResponse mockResponse = new VueloResponse();
        Mockito.when(vueloServicio.buscarVuelos("MAD", "BCN", "2024-05-20", null))
                .thenReturn(mockResponse);

        // When / Then
        mockMvc.perform(MockMvcRequestBuilders.get("/vuelos")
                        .param("origen", "MAD")
                        .param("destino", "BCN")
                        .param("fechaIda", "2024-05-20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void buscarAeropuertosOk() throws Exception {
        // Given
        AeropuertoResponse aeropuerto = new AeropuertoResponse();
        aeropuerto.setCodigoAeropuerto("MAD");
        aeropuerto.setPais("ES");

        List<AeropuertoResponse> mockResponse = Arrays.asList(aeropuerto);
        Mockito.when(vueloServicio.buscarAeropuertos("Madrid"))
                .thenReturn(mockResponse);

        // When / Then
        mockMvc.perform(MockMvcRequestBuilders.get("/aeropuertos")
                        .param("nombreCiudad", "Madrid"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].codigoAeropuerto").value("MAD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pais").value("ES"));
    }

    /*@Test
    void crearReservaOk() throws Exception {
        // Given
        String requestBody = "{\"vueloId\":\"123\",\"pasajero\":\"John Doe\"}";

        // When / Then
        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }*/

    @Test
    void buscarVuelosFaltanParametros() throws Exception {
        // When / Then
        mockMvc.perform(MockMvcRequestBuilders.get("/vuelos")
                        .param("origen", "MAD")
                        .param("destino", "BCN"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}