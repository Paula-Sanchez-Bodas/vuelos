package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;

import java.util.List;

public class ItinerarioResponse {
    private List<SeguimientoResponse> listaSeguimientos;

    public List<SeguimientoResponse> getListaSeguimientos() {
        return listaSeguimientos;
    }

    public void setListaSeguimientos(List<SeguimientoResponse> listaSeguimientos) {
        this.listaSeguimientos = listaSeguimientos;
    }
}
