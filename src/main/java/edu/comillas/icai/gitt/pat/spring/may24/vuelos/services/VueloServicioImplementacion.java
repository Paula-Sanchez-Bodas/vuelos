package edu.comillas.icai.gitt.pat.spring.may24.vuelos.services;

import com.fasterxml.jackson.databind.JsonNode;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.entity.ReservaEntity;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.repositories.ReservaRepo;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class VueloServicioImplementacion implements VueloServicio{
    @Autowired  ReservaRepo reservaRepo;
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
    public VueloResponse buscarVuelos(String origen, String destino, String fechaIda, String fechaVuelta) {
        return webClient.get().uri( uriBuilder ->
                        uriBuilder.path("/v2/shopping/flight-offers")
                                .queryParam("originLocationCode", origen)
                                .queryParam("destinationLocationCode", destino)
                                .queryParam("departureDate", fechaIda)
                                .queryParam("returnDate", fechaVuelta)
                                .queryParam("adults", "1")
                                .queryParam("max", "5")
                                .build())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(getAccessToken()))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    VueloResponse vueloResponse=new VueloResponse();
                    List<OfertaResponse> ofertasResponse = new ArrayList<>();

                    for(JsonNode jsonNode : json.withArrayProperty("data")){
                        OfertaResponse ofertaResponse= new OfertaResponse();
                        List<ItinerarioResponse> itinerarios = retrieveItinerarios(jsonNode);
                        ofertaResponse.setItinerarioIda(itinerarios.get(0));
                        ofertaResponse.setItinerarioVuelta(itinerarios.get(1));
                        double precio = jsonNode.get("price").get("total").asDouble();
                        ofertaResponse.setPrecio(precio);
                        ofertasResponse.add(ofertaResponse);
                    }
                    vueloResponse.setListaOfertas(ofertasResponse);
                    return vueloResponse;
                })
                .block();
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

    @Override
    public ReservaEntity reservar(ReservaEntity reservaRequest) {
        return reservaRepo.save(reservaRequest);
    }

    private List<ItinerarioResponse> retrieveItinerarios(JsonNode jsonNode) {
        JsonNode itinerarios= jsonNode.withArrayProperty("itineraries");
        List<ItinerarioResponse> listaItinerarios=new ArrayList<>();
        for(JsonNode jsonNodeItinerario: itinerarios){
            ItinerarioResponse itinerarioResponse=new ItinerarioResponse();
            JsonNode seguimientos= jsonNodeItinerario.withArrayProperty("segments");
            List<SeguimientoResponse> listaSeguimientos= new ArrayList<>();
            for(JsonNode jsonNodeSeguimiento: seguimientos){
                SeguimientoResponse seguimientoResponse2= new SeguimientoResponse();
                seguimientoResponse2.setOrigen(jsonNodeSeguimiento.get("departure").get("iataCode").asText());
                seguimientoResponse2.setDestino(jsonNodeSeguimiento.get("arrival").get("iataCode").asText());
                seguimientoResponse2.setFechaSalida(jsonNodeSeguimiento.get("departure").get("at").asText());
                seguimientoResponse2.setFechaRegreso(jsonNodeSeguimiento.get("arrival").get("at").asText());
                listaSeguimientos.add(seguimientoResponse2);
            }
            itinerarioResponse.setListaSeguimientos(listaSeguimientos);
            listaItinerarios.add(itinerarioResponse);
        }
        return listaItinerarios;
    }

}
