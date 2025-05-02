package com.giffuniscode.pgm.core.services;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.giffuniscode.infraestructure.GenericRequest;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Vehicle;

import java.util.List;

public class PgmService {

    private static final String VEHICLE_CONTROLLER = "vehicles/";
    private static final String UPDATE_METHOD = "update/";
    private static final String CREATE_METHOD = "add/";
    private static final String DELETE_METHOD = "delete?id=";
    private static final String BY_ID_METHOD = "ById?id=";

    private Context ctx;

    public PgmService(Context ctx) {
        this.ctx = ctx;
    }

    public void getVehicles(Response.Listener<PgmArrayResponse> listener, Response.ErrorListener errorListener) {
        GenericRequest<PgmArrayResponse> request = new GenericRequest<PgmArrayResponse>(
                getUrlBase() + VEHICLE_CONTROLLER,
                PgmArrayResponse.class,
                listener,
                errorListener
        );

        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public void updateVehicle(Vehicle vehicle, Response.Listener<PgmResponse> listener, Response.ErrorListener requestErrorListener) {
        GenericRequest<PgmResponse> request = new GenericRequest<PgmResponse>(
                Request.Method.POST,
                getUrlBase() + VEHICLE_CONTROLLER + UPDATE_METHOD,
                PgmResponse.class,
                vehicle,
                listener,
                requestErrorListener
        );

        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public void addVehicle(Vehicle vehicle, Response.Listener<PgmResponse> listener, Response.ErrorListener requestErrorListener) {
        GenericRequest<PgmResponse> request = new GenericRequest<PgmResponse>(
                Request.Method.POST,
                getUrlBase() + VEHICLE_CONTROLLER + CREATE_METHOD,
                PgmResponse.class,
                vehicle,
                listener,
                requestErrorListener
        );

        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public void deleteVehicle(Vehicle vehicle, Response.Listener<PgmBoolResponse> listener, Response.ErrorListener errorListener) {
                GenericRequest<PgmBoolResponse> request = new GenericRequest<PgmBoolResponse>(
                Request.Method.POST,
                getUrlBase() + VEHICLE_CONTROLLER + DELETE_METHOD + vehicle.getId().toString(),
                PgmBoolResponse.class,
                null,
                listener,
                errorListener
        );
        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public String getShareLink(Vehicle vehicle){
        return getUrlBase() + VEHICLE_CONTROLLER + BY_ID_METHOD + vehicle.getId().toString();
    }

    private String getUrlBase() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);

        String url = pref.getBoolean("https", false) ? "https://" : "http://";
        url += pref.getString("hostIp", "");

        return url;
    }

    public static class PgmResponse {
        public Vehicle value;
        public List<String> errors;
    }

    public static class PgmArrayResponse {
        public List<Vehicle> value;
        public List<String> errors;
    }

    public static class PgmBoolResponse {
        public Boolean value;
        public List<String> errors;
    }

}
