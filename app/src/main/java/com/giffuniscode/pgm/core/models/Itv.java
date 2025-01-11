package com.giffuniscode.pgm.core.models;

import java.util.Date;

public class Itv {
    public enum Status { FAVORABLE, ADVERSE, REFUSAL}

    private Enum<Status> status;
    private int odometer;
    private Date startDate;
    private Date endDate;

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
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

