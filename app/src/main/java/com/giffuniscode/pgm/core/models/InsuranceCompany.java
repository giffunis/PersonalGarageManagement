package com.giffuniscode.pgm.core.models;

import java.util.Date;

public class InsuranceCompany {
    public enum InsuranceType {THIRD_PARTY, THIRD_PARTY_WITH_FIRE_AND_THEFT,COMPREHENSIVE}

    private String company;
    private String policyNumber;
    private String policyHolder;
    private String mainDriver;
    private String additionalDriver;
    private Enum<InsuranceType> insurance;
    private Date startDate;
    private Date endDate;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }

    public String getMainDriver() {
        return mainDriver;
    }

    public void setMainDriver(String mainDriver) {
        this.mainDriver = mainDriver;
    }

    public String getAdditionalDriver() {
        return additionalDriver;
    }

    public void setAdditionalDriver(String additionalDriver) {
        this.additionalDriver = additionalDriver;
    }

    public Enum<InsuranceType> getInsurance() {
        return insurance;
    }

    public void setInsurance(Enum<InsuranceType> insurance) {
        this.insurance = insurance;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

