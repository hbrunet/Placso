package com.playcasinos.placso.serviceagents.proxies.sorteomodule;

import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Pair;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.security.InvalidParameterException;

/**
 * Created by hbrunet on 02/08/2016.
 */
public class AsyncCallWS extends AsyncTask<Pair<String, Object>, Void, SoapObject> {

    @Override
    protected SoapObject doInBackground(Pair<String, Object>... params) {

        if (params.length < 2)
            throw  new InvalidParameterException("params");

        try {
            SoapObject Request = new SoapObject(params[0].second.toString(), params[1].second.toString());

            for (Pair<String, Object> param : params) {
                if (!param.first.equals("soapAction") && !param.first.equals("methodName")){
                    Request.addProperty(param.first, param.second);
                }
            }

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(params[2].second.toString());

            transport.call(params[3].second.toString(), soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();
            return response;

        } catch (Exception ex) {

        }

        return null;
    }
}