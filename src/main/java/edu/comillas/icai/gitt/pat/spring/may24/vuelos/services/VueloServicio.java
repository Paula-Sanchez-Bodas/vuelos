package edu.comillas.icai.gitt.pat.spring.may24.vuelos.services;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.entity.ReservaEntity;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;

import java.util.List;

public interface VueloServicio {

    String getAccessToken();
    VueloResponse buscarVuelos(String origen, String destino, String fechaIda, String fechaVuelta);
    List<AeropuertoResponse> buscarAeropuertos(String nombreCiudad);

    ReservaEntity reservar(ReservaEntity reservaRequest);

    List<ReservaEntity> buscarReservas(String usuario);
}
