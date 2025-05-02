package com.giffuniscode.pgm.core.models;

import com.google.gson.Gson;

import java.util.Date;

public class Vehicle {

    private long id;
    private String licensePlate;
    private String manufacturer;
    private String manufacturerLogoUrl;
    private String model;
    private Date firstRegistration;
    private String photoUrl;

    public Vehicle() {
        firstRegistration = new Date(System.currentTimeMillis());
    }

    public static String toJson(Vehicle vehicle){
        return new Gson().toJson(vehicle);
    }

    public static Vehicle fromJson(String json){
        return new Gson().fromJson(json, Vehicle.class);
    }

    public Long getId() { return id; }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerLogo() {
        return manufacturerLogoUrl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String toJsonString() { return new Gson().toJson(this);}
}
