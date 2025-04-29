package edu.comillas.icai.gitt.pat.spring.may24.vuelos.controllers;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.OfertaResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.services.VueloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class VueloController {

    @Autowired VueloServicio vueloServicio;


    @GetMapping("/vuelos")
    List<VueloResponse> getVuelos(@RequestParam String origen,
                                  @RequestParam String destino,
                                  @RequestParam Date fechaIda,
                                  @RequestParam(required = false) Date fechaVuelta){
        return vueloServicio.buscarVuelos(origen, destino, fechaIda, fechaVuelta);

    }

    @GetMapping("/aeropuertos")
    List<AeropuertoResponse> getAeropuertos(@RequestParam String nombreCiudad){
        return vueloServicio.buscarAeropuertos(nombreCiudad);
    }

    @PostMapping("/reservas")
    void postReserva(@RequestBody String datosReserva){

    }



}
