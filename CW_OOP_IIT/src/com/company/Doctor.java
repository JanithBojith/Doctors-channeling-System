package com.company;

import java.io.Serializable;

public class Doctor extends Person implements Serializable {
    private String medicalLicenceNumber;
    private String specialisation;

    Doctor(String name, String surName, String dateOfBirth, String mobileNumber,String  medicalLicenceNumber,String specialisation) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber=medicalLicenceNumber;
        this.specialisation=specialisation;
    }




    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }
    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }
    public void setSpecialisation(String specialization){
        this.specialisation=specialisation;
    }
    public String getSpecialisation(){
        return specialisation;
    }
}

