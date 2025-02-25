package com.giffuniscode.pgm.core.services;

import android.content.Context;

import com.giffuniscode.infraestructure.GsonRequest;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Vehicle;

import java.util.List;

public class PgmService {

    private static final String URL_BASE = "http://172.30.140.234:5192/";
    private static final String VEHICLE_CONTROLLER = "vehicles/";

    public static void GetVehicles(Context ctx, com.android.volley.Response.Listener<PgmResponse> listener, com.android.volley.Response.ErrorListener requestErrorListener){

        GsonRequest<PgmResponse> gsonRequest = new GsonRequest(
                URL_BASE + VEHICLE_CONTROLLER,//URL
                PgmResponse.class,//Clase a la que se convertira el JSON
                null,//encabezado no necesitamos
                listener,//listener
                requestErrorListener//listener
        );

        VolleyClient.getInstance(ctx).addToRequestQueue(gsonRequest);
    }

    public static class PgmResponse {
        public List<Vehicle> value;
        public List<String> errors;
    }

}
