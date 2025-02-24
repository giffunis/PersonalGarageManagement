package com.giffuniscode.pgm.core.models;

import com.giffuniscode.db.sqlite.BaseEntity;

import java.util.List;

public class Vehicle extends BaseEntity {
    private String model;
    private String manufacturer;
    private String originalRegistrationDate;
    private String vin;
    private String licensePlate;
    private String FuelCapacity;
    private String brandLogoUrl;

    public Vehicle(String licensePlate, String brandLogoUrl) {
        this.licensePlate = licensePlate;
        this.brandLogoUrl = brandLogoUrl;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOriginalRegistrationDate() {
        return originalRegistrationDate;
    }

    public void setOriginalRegistrationDate(String originalRegistrationDate) {
        this.originalRegistrationDate = originalRegistrationDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getFuelCapacity() {
        return FuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        FuelCapacity = fuelCapacity;
    }

}
