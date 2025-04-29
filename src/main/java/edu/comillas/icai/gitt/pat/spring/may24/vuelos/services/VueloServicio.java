package edu.comillas.icai.gitt.pat.spring.may24.vuelos.services;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;

import java.util.Date;
import java.util.List;

public interface VueloServicio {

    List<VueloResponse> buscarVuelos(String origen, String destino, Date fechaIda, Date fechaVuelta);
    List<AeropuertoResponse> buscarAeropuertos(String nombreCiudad);

}
