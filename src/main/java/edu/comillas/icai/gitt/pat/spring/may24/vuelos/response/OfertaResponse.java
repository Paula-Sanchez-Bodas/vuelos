package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;

import jakarta.validation.constraints.Positive;

public class OfertaResponse {

    private ItinerarioResponse itinerarioIda;
    private ItinerarioResponse itinerarioVuelta;
    @Positive
    private double precio;

    public ItinerarioResponse getItinerarioIda() {
        return itinerarioIda;
    }

    public void setItinerarioIda(ItinerarioResponse itinerarioIda) {
        this.itinerarioIda = itinerarioIda;
    }

    public ItinerarioResponse getItinerarioVuelta() {
        return itinerarioVuelta;
    }

    public void setItinerarioVuelta(ItinerarioResponse itinerarioVuelta) {
        this.itinerarioVuelta = itinerarioVuelta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
