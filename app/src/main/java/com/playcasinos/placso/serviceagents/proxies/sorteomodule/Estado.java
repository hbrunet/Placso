package com.playcasinos.placso.serviceagents.proxies.sorteomodule;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class Estado {
    private boolean habilitado;
    private String[] salas;

    public Estado(boolean habilitado, String[] salas) {
        this.habilitado = habilitado;
        this.salas = salas;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String[] getSalas() {
        return salas;
    }
}
