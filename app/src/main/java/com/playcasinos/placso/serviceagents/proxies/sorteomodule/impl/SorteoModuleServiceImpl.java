package com.playcasinos.placso.serviceagents.proxies.sorteomodule.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Pair;

import com.playcasinos.placso.serviceagents.proxies.sorteomodule.AsyncCallWS;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Estado;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.Sorteo;
import com.playcasinos.placso.serviceagents.proxies.sorteomodule.SorteoModuleService;

import org.ksoap2.serialization.SoapObject;

import java.net.URI;
import java.util.concurrent.ExecutionException;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class SorteoModuleServiceImpl implements SorteoModuleService {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private static final String NAME_SPACE = "http://SQR.IT.Contracts.Service";
    private final String url;

    public SorteoModuleServiceImpl(Context context)
    {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        url = String.format("http://{0}:{1}/SQRService", sharedPreferences.getString("ip_webservice_preference", ""),
                sharedPreferences.getString("puerto_webservice_preference", ""));
    }

    @Override
    public Sorteo iniciarSorteoMovil(String mac) {

        try {
            AsyncCallWS asyncCallWS = new AsyncCallWS();
            asyncCallWS.execute(
                    new Pair<String, Object>("nameSpace", NAME_SPACE),
                    new Pair<String, Object>("methodName", "IniciarSorteoMovil"),
                    new Pair<String, Object>("url", url),
                    new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/IniciarSorteoMovil"),
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
            asyncCallWS.execute(
                    new Pair<String, Object>("nameSpace", NAME_SPACE),
                    new Pair<String, Object>("methodName", "ComprobarEstadoEntregas"),
                    new Pair<String, Object>("url", url),
                    new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ComprobarEstadoEntregas"),
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
        asyncCallWS.execute(
                new Pair<String, Object>("nameSpace", NAME_SPACE),
                new Pair<String, Object>("methodName", "EnviarDigitoSorteado"),
                new Pair<String, Object>("url", url),
                new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/EnviarDigitoSorteado"),
                new Pair<String, Object>("codigo", codigo),
                new Pair<String, Object>("orden", orden),
                new Pair<String, Object>("digito", digito));
    }

    @Override
    public void reiniciarSorteo(String codigo) {
        AsyncCallWS asyncCallWS = new AsyncCallWS();
        asyncCallWS.execute(
                new Pair<String, Object>("nameSpace", NAME_SPACE),
                new Pair<String, Object>("methodName", "ReiniciarSorteo"),
                new Pair<String, Object>("url", url),
                new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ReiniciarSorteo"),
                new Pair<String, Object>("codigo", codigo));
    }

    @Override
    public void confirmarNumeroSorteado(String codigo, int numero) {
        AsyncCallWS asyncCallWS = new AsyncCallWS();
        asyncCallWS.execute(
                new Pair<String, Object>("nameSpace", NAME_SPACE),
                new Pair<String, Object>("methodName", "ConfirmarNumeroSorteado"),
                new Pair<String, Object>("url", url),
                new Pair<String, Object>("soapAction", "http://SQR.IT.Contracts.Service/IIdentifyCards/ConfirmarNumeroSorteado"),
                new Pair<String, Object>("codigo", codigo),
                new Pair<String, Object>("numero", numero));
    }
}
