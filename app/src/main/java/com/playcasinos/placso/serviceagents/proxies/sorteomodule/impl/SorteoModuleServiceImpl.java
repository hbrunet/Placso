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
            asyncCallWS.execute(new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard"),
                    new Pair<String, Object>("methodName", "ReadCard"),
                    new Pair<String, Object>("mac", mac));

            SoapObject result = asyncCallWS.get();
            Sorteo sorteo = new Sorteo((int)result.getProperty("Cantidad"), result.getPropertyAsString("Codigo"));
            return  sorteo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Estado comprobarEstadoEntregas(String codigo) {
        try {
            AsyncCallWS asyncCallWS = new AsyncCallWS();
            asyncCallWS.execute(new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard"),
                    new Pair<String, Object>("methodName", "ReadCard"),
                    new Pair<String, Object>("codigo", codigo));

            SoapObject result = asyncCallWS.get();
            Estado estado = new Estado((boolean)result.getProperty("Habilitado"), (String[])result.getProperty("Salas"));
            return  estado;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void enviarDigitoSorteado(String codigo, int orden, int digito) {
        AsyncCallWS asyncCallWS = new AsyncCallWS();
        asyncCallWS.execute(new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard"),
                new Pair<String, Object>("methodName", "ReadCard"),
                new Pair<String, Object>("codigo", codigo),
                new Pair<String, Object>("orden", orden),
                new Pair<String, Object>("digito", digito));
    }

    @Override
    public void reiniciarSorteo(String codigo) {
        AsyncCallWS asyncCallWS = new AsyncCallWS();
        asyncCallWS.execute(new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard"),
                new Pair<String, Object>("methodName", "ReadCard"),
                new Pair<String, Object>("codigo", codigo));
    }

    @Override
    public void confirmarNumeroSorteado(String codigo, int numero) {
        AsyncCallWS asyncCallWS = new AsyncCallWS();
        asyncCallWS.execute(new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard"),
                new Pair<String, Object>("methodName", "ReadCard"),
                new Pair<String, Object>("codigo", codigo),
                new Pair<String, Object>("numero", numero));
    }
}
