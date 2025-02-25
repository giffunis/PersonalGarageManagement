package com.giffuniscode.pgm.core.services;

import android.content.Context;

import com.giffuniscode.infraestructure.GsonRequest;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Response;

public class PgmService {

    private static final String URL_BASE = "http://172.30.140.234:5192/";
    private static final String VEHICLE_CONTROLLER = "vehicles/";

    public static void GetVehicles(Context ctx, com.android.volley.Response.Listener<Response> listener, com.android.volley.Response.ErrorListener requestErrorListener){

        GsonRequest<Response> gsonRequest = new GsonRequest(
                URL_BASE + VEHICLE_CONTROLLER,//URL
                Response.class,//Clase a la que se convertira el JSON
                null,//encabezado no necesitamos
                listener,//listener
                requestErrorListener//listener
        );

        VolleyClient.getInstance(ctx).addToRequestQueue(gsonRequest);
    }

}
