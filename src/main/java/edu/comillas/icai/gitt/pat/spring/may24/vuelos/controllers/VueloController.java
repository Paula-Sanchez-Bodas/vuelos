package edu.comillas.icai.gitt.pat.spring.may24.vuelos.controllers;

import edu.comillas.icai.gitt.pat.spring.may24.vuelos.entity.ReservaEntity;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.AeropuertoResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.response.VueloResponse;
import edu.comillas.icai.gitt.pat.spring.may24.vuelos.services.VueloServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VueloController {

    @Autowired VueloServicio vueloServicio;


    @GetMapping("/vuelos")
    VueloResponse getVuelos(@RequestParam String origen,
                                  @RequestParam String destino,
                                  @RequestParam String fechaIda,
                                  @RequestParam(required = false) String fechaVuelta){
        return vueloServicio.buscarVuelos(origen, destino, fechaIda, fechaVuelta);

    }

    @GetMapping("/aeropuertos")
    List<AeropuertoResponse> getAeropuertos(@RequestParam String nombreCiudad){
        return vueloServicio.buscarAeropuertos(nombreCiudad);
    }

    @PostMapping("/reservas")
    @ResponseStatus(HttpStatus.CREATED)
    ReservaEntity postReserva(@RequestBody @Valid ReservaEntity datosReserva){
        return vueloServicio.reservar(datosReserva);
    }

    @GetMapping("/reservas")
    List<ReservaEntity> reservasUsuario(@RequestParam(required = true) String usuario){
        return vueloServicio.buscarReservas(usuario);
    }

}
