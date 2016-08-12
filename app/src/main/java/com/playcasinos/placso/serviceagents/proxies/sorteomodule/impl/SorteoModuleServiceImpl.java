package com.playcasinos.placso.serviceagents.proxies.sorteomodule.impl;

import android.util.Pair;

import com.playcasinos.placso.serviceagents.proxies.sorteomodule.AsyncCallWS;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Estado;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Sorteo;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.SorteoModuleService;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class SorteoModuleServiceImpl implements SorteoModuleService {

    @Override
    public Sorteo iniciarSorteoMovil(String mac) {

        try {
            AsyncCallWS asyncCallWS = new AsyncCallWS();
            asyncCallWS.execute(new Pair<String, Object>("soapAction", ""),
                    new Pair<String, Object>("methodName", ""),
                    new Pair<String, Object>("mac", mac));

            SoapObject result = asyncCallWS.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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
