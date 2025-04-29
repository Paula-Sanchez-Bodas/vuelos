package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;

import java.util.List;

public class VueloResponse {
    private List<OfertaResponse> listaOfertas;

    public List<OfertaResponse> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(List<OfertaResponse> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
}
