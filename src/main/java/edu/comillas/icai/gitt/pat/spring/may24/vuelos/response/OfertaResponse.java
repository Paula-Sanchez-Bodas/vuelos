package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;

public class OfertaResponse {

    private ItinerarioResponse itinerarioIda;
    private ItinerarioResponse itinerarioVuelta;
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
