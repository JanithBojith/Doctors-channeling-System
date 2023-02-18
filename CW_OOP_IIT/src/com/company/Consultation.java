package com.company;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Patient{
    private String drID;
    private int charges;
    private LocalTime consulStart;
    private LocalTime consulEnd;
    private LocalDate date;
    private String notes;

    public Consultation(String name, String surName,String dateOfBirth,String mobileNumber,int patientId,String drID, LocalTime consulStart, LocalTime consulEnd , LocalDate date,String notes,int charges) {
        super(name, surName,dateOfBirth,mobileNumber,patientId);
        this.drID = drID;
        this.charges = charges;
        this.date =date;
        this.notes=notes;
        this.consulStart = consulStart;
        this.consulEnd = consulEnd;
    }
    public LocalTime getConsulStart()
    {return consulStart;}
    public void setConsulStart(LocalTime consulStart)
    {this.consulStart=consulStart;}

    public LocalTime getConsulEnd()
    {return consulEnd;}
    public void setConsulEnd(LocalTime consulEnd)
    {this.consulEnd=consulEnd;}

    public String getnotes()
    {return notes;}
    public void setnotes(String notes)
    {this.notes=notes;}


    public String getdrID() {
        return drID;
    }

    public void setdrID(String drID) {
        this.drID = drID;
    }

    public int getcharges() {
        return charges;
    }

    public void setcharges(int charges) {
        this.charges= charges;
    }

    public LocalDate getdate() {
        return date;
    }

    public void setdateAndTime(LocalDate date) {
        this.date =date;
    }



    @Override
    public String toString() {
        return  "01.Name                : " + this.getName() + '\n' +
                "02.SurName             : " + this.getSurName() + '\n' +
                "03.Date-of-Birth       : " + this.getDateOfBirth() + '\n' +
                "04.Mobile-No           : " + this.getMobileNumber() + '\n' +
                "05.Patient-ID          : " + this.getpatientId() + '\n' +
                "06.Consultation-Date   : " + date +'\n'+
                "07.Start-Time          : " + consulStart +'\n'+
                "08.End-Time            : " + consulEnd +'\n'+
                "09.Consulted-Doctor    : " + drID +'\n'+
                "10.Cost-for-Consulation: " + charges+'\n'+
                "11.Additional-Note  : \n" + notes +'\n';
    }
}
