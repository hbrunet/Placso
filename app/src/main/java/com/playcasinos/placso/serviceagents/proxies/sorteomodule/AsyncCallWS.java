package com.playcasinos.placso.serviceagents.proxies.sorteomodule;

import android.os.AsyncTask;
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

    private static final String SOAP_ACTION = "http://SQR.IT.Contracts.Service/IIdentifyCards/ReadCard";
    private static final String METHOD_NAME = "ReadCard";
    private static final String NAMESPACE = "http://SQR.IT.Contracts.Service";
    private static String URL = "";

    @Override
    protected SoapObject doInBackground(Pair<String, Object>... params) {

        if (params.length < 2)
            throw  new InvalidParameterException("params");

        try {
            SoapObject Request = new SoapObject(NAMESPACE, params[1].second.toString());

            for (Pair<String, Object> param : params) {
                if (!param.first.equals("soapAction") && !param.first.equals("methodName")){
                    Request.addProperty(param.first, param.second);
                }
            }

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(params[0].second.toString(), soapEnvelope);
            SoapObject response = (SoapObject) soapEnvelope.getResponse();
            return response;

        } catch (Exception ex) {

        }
        return null;
    }
}