package com.playcasinos.placso.serviceagents.proxies.sorteomodule;

/**
 * Created by DEVNET on 28/07/2016.
 */
public interface SorteoModuleService {
    Sorteo iniciarSorteoMovil(String mac);
    Estado comprobarEstadoEntregas(String codigo); // devuelve clase estado
    void enviarDigitoSorteado(String codigo, int orden, int digito);
    void reiniciarSorteo(String codigo);
    void confirmarNumeroSorteado(String codigo, int numero);
}
