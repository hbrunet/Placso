package com.playcasinos.placso.serviceagents.proxies.sorteomodule.impl;

import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Estado;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Sorteo;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.SorteoModuleService;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class SorteoModuleServiceImpl implements SorteoModuleService {

    @Override
    public Sorteo iniciarSorteoMovil(String mac) {
        return null;
    }

    @Override
    public Estado comprobarEstadoEntregas(String codigo) {
        return  null;
    }

    @Override
    public void enviarDigitoSorteado(String codigo, int orden, int digito) {

    }

    @Override
    public void reiniciarSorteo(String codigo) {

    }

    @Override
    public void confirmarNumeroSorteado(String codigo, int numero) {

    }
}
