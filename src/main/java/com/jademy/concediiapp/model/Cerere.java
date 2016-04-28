package com.jademy.concediiapp.model;

import java.util.Date;

public class Cerere {

    private int requestID;
    private String tipConcediu;
    private int duration;
    private String status;
    private Date dataStart;
    private Date dataFinal;
    private int userID;

    public int getRequestID() {
        return requestID;
    }

    public String getTipConcediu() {
        return tipConcediu;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public int getUserID() {
        return userID;
    }

    private Cerere() {

    }

    //contains all setters
    public static class Builder {

        Cerere cerere = new Cerere();

        public Builder setID(int ID) {
            cerere.requestID = ID;
            return this;
        }

        public Builder setTipConcediu(String tipConcediu) {
            cerere.tipConcediu = tipConcediu;
            return this;
        }

        public Builder setDuration(int duration) {
            cerere.duration = duration;
            return this;
        }

        public Builder setStatus(String status) {
            cerere.status = status;
            return this;
        }

        public Builder setDataStart(Date dataStart) {
            cerere.dataStart = dataStart;
            return this;
        }

        public Builder setDataFinal(Date dataFinal) {
            cerere.dataFinal = dataFinal;
            return this;
        }

        public Builder setUserID(int userID) {
            cerere.userID = userID;
            return this;
        }

        public Cerere build() {
            return cerere;
        }

    }

}
