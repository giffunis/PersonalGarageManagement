package com.giffuniscode.pgm.core.models;

import com.giffuniscode.db.sqlite.BaseEntity;
import com.google.gson.Gson;

import java.util.Date;

public class Vehicle {

    private String licensePlate;
    private String manufacturer;
    private String manufacturerLogoUrl;
    private String model;
    private Date firstRegistration;
    private String photoUrl;

    public Vehicle() {
    }

    public static String toJson(Vehicle vehicle){
        return new Gson().toJson(vehicle);
    }

    public static Vehicle fromJson(String json){
        return new Gson().fromJson(json, Vehicle.class);
    }

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

    public void setManufacturerLogo(String manufacturerLogoUrl) {
        this.manufacturerLogoUrl = manufacturerLogoUrl;
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
}
