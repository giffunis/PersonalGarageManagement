package com.giffuniscode.pgm.core.services;

import android.content.Context;

import com.android.volley.Request;
import com.giffuniscode.infraestructure.GenericRequest;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.List;

public class PgmService {

    private static final String URL_BASE = "http://192.168.0.110:5192/";
    private static final String VEHICLE_CONTROLLER = "vehicles/";
    private static final String UPDATE_METHOD = "update/";
    private static final String CREATE_METHOD = "add/";
    private static final String DELETE_METHOD = "delete?";

    private Context ctx;

    public PgmService(Context ctx) {
        this.ctx = ctx;
    }

    public void GetVehicles(Response.Listener<PgmArrayResponse> listener, Response.ErrorListener errorListener) {
        GenericRequest<PgmArrayResponse> request = new GenericRequest<PgmArrayResponse>(
                URL_BASE + VEHICLE_CONTROLLER,
                PgmArrayResponse.class,
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

    public void DeleteVehicle(Vehicle vehicle, Response.Listener<PgmBoolResponse> listener, Response.ErrorListener errorListener) {
                GenericRequest<PgmBoolResponse> request = new GenericRequest<PgmBoolResponse>(
                Request.Method.POST,
                URL_BASE + VEHICLE_CONTROLLER + DELETE_METHOD +"id=" + vehicle.getId().toString(),
                PgmBoolResponse.class,
                null,
                listener,
                errorListener
        );
        VolleyClient.getInstance(this.ctx).addToRequestQueue(request);
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
