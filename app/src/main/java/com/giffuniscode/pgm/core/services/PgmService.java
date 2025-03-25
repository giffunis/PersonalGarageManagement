package com.giffuniscode.pgm.core.services;

import android.content.Context;

import com.android.volley.Request;
import com.giffuniscode.infraestructure.GenericRequest;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.android.volley.Response;

import java.util.List;

public class PgmService {

    private static final String URL_BASE = "http://192.168.0.110:5192/";
    private static final String VEHICLE_CONTROLLER = "vehicles/";
    private static final String UPDATE_METHOD = "update/";
    private static final String CREATE_METHOD = "add/";

    private Context ctx;

    public PgmService(Context ctx) {
        this.ctx = ctx;
    }

    public void GetVehicles(Response.Listener<PgmResponse> listener, Response.ErrorListener errorListener) {
        GenericRequest<PgmResponse> request = new GenericRequest<PgmResponse>(
                URL_BASE + VEHICLE_CONTROLLER,
                PgmResponse.class,
                listener,
                errorListener
        );

        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public void updateVehicle(Vehicle vehicle, Response.Listener<PgmResponse> listener, Response.ErrorListener requestErrorListener) {
        GenericRequest<PgmResponse> request = new GenericRequest<PgmResponse>(
                Request.Method.POST,
                URL_BASE + VEHICLE_CONTROLLER + UPDATE_METHOD,
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
                URL_BASE + VEHICLE_CONTROLLER + CREATE_METHOD,
                PgmResponse.class,
                vehicle,
                listener,
                requestErrorListener
        );

        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
    }

    public void DeleteVehicle(Vehicle vehicle, Response.Listener<PgmResponse> listener, Response.ErrorListener requestErrorListener) {

    }

    public static class PgmResponse {
        public List<Vehicle> value;
        public List<String> errors;
    }

}
