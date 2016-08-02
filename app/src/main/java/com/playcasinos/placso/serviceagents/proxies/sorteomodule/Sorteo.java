package com.playcasinos.placso.serviceagents.proxies.sorteomodule;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class Sorteo {
    private int cantidad;
    private String codigo;

    public Sorteo(int cantidad, String codigo) {
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCodigo() {
        return codigo;
    }
}
