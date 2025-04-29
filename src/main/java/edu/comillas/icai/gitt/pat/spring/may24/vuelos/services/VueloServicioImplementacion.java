package edu.comillas.icai.gitt.pat.spring.may24.vuelos.services;

import com.fasterxml.jackson.databind.JsonNode;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VueloServicioImplementacion implements VueloServicio{

    @Value("${vuelos.client_id}")
    private String client_id;

    @Value("${vuelos.client_secret}")
    private String client_secret;

    private final WebClient webClient;

    public VueloServicioImplementacion(WebClient.Builder builder){
        webClient=builder.baseUrl("https://test.api.amadeus.com").build();
    }


    @Override
    public String getAccessToken() {

        return webClient.post().uri("/v1/security/oauth2/token")
                .body(BodyInserters.fromFormData("client_id",client_id)
                        .with("client_secret",client_secret)
                        .with("grant_type","client_credentials"))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("access_token").asText())
                .block();
    }

    @Override
    public List<VueloResponse> buscarVuelos(String origen, String destino, Date fechaIda, Date fechaVuelta) {
        VueloResponse vueloResponse= new VueloResponse();
        List<VueloResponse> listaVuelos= new ArrayList<>();
        listaVuelos.add(vueloResponse);
        return listaVuelos;
    }

    @Override
    public List<AeropuertoResponse> buscarAeropuertos(String nombreCiudad) {
        return webClient.get().uri( uriBuilder ->
                uriBuilder.path("/v1/reference-data/locations")
                        .queryParam("subType", "AIRPORT")
                        .queryParam("keyword", nombreCiudad)
                        .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(getAccessToken()))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    List<AeropuertoResponse> listaRespuesta= new ArrayList<>();
                     for(JsonNode jsonNode : json.withArrayProperty("data")){
                        AeropuertoResponse aeropuertoResponse=new AeropuertoResponse();
                        String codidoPais= jsonNode.get("address").get("countryCode").asText();
                        String codigoAeropuerto= jsonNode.get("iataCode").asText();
                        aeropuertoResponse.setPais(codidoPais);
                        aeropuertoResponse.setCodigoAeropuerto(codigoAeropuerto);
                        listaRespuesta.add(aeropuertoResponse);
                    }
                    return listaRespuesta;
                    })
                .block();
    }

}
