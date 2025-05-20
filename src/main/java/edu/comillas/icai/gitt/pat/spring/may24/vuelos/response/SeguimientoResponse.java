package edu.comillas.icai.gitt.pat.spring.may24.vuelos.response;


import jakarta.validation.constraints.NotBlank;

public class SeguimientoResponse {
    @NotBlank
    private String origen;
    @NotBlank
    private String destino;
    @NotBlank
    private String fechaSalida;
    @NotBlank
    private String fechaRegreso;

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }
}
