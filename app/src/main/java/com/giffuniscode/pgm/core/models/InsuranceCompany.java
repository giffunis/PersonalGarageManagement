package com.giffuniscode.pgm.core.models;

import java.util.Date;

public class InsuranceCompany {
    private String company;
    private String policyNumber;
    private String policyHolder;
    private String mainDriver;
    private String additionalDriver;
    private Enum<InsuranceType> insurance;
    private Date startDate;
    private Date endDate;
}

enum InsuranceType {THIRD_PARTY, THIRD_PARTY_WITH_FIRE_AND_THEFT,COMPREHENSIVE}