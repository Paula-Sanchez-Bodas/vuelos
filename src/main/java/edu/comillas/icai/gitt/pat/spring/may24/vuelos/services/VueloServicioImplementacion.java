package edu.comillas.icai.gitt.pat.spring.may24.vuelos.services;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VueloServicioImplementacion implements VueloServicio{


    @Override
    public List<VueloResponse> buscarVuelos(String origen, String destino, Date fechaIda, Date fechaVuelta) {
        VueloResponse vueloResponse= new VueloResponse();
        List<VueloResponse> listaVuelos= new ArrayList<>();
        listaVuelos.add(vueloResponse);
        return listaVuelos;
    }

    @Override
    public List<AeropuertoResponse> buscarAeropuertos(String nombreCiudad) {
        AeropuertoResponse aeropuertoResponse=new AeropuertoResponse();
        aeropuertoResponse.setCodigoAeropuerto("PAR");
        aeropuertoResponse.setPais("FR");
        List<AeropuertoResponse> listaAeropuertos=new ArrayList<>();
        listaAeropuertos.add(aeropuertoResponse);
        return listaAeropuertos;
    }

}
