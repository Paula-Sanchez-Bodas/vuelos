package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VueloResponse {
    @NotNull(message = "La lista de ofertas no puede ser nula")
    private List<OfertaResponse> listaOfertas;

    public List<OfertaResponse> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(List<OfertaResponse> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
}
