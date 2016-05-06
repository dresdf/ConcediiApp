package com.jademy.concediiapp.model;

import java.util.Date;

public class Application {

    private int requestID;
    private String tipConcediu;
    private int duration;
    private String status;
    private Date dataStart;
    private Date dataFinal;
    private int userID;

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getTipConcediu() {
        return tipConcediu;
    }

    public void setTipConcediu(String tipConcediu) {
        this.tipConcediu = tipConcediu;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

//</editor-fold>
    public Application() {
        //empty 
    }

    //builder for Application
    public static class Builder {

        Application cerere = new Application();

        public Builder setID(int ID) {
            cerere.setRequestID(ID);
            return this;
        }

        public Builder setTipConcediu(String tipConcediu) {
            cerere.setTipConcediu(tipConcediu);
            return this;
        }

        public Builder setDuration(int duration) {
            cerere.setDuration(duration);
            return this;
        }

        public Builder setStatus(String status) {
            cerere.setStatus(status);
            return this;
        }

        public Builder setDataStart(Date dataStart) {
            cerere.setDataStart(dataStart);
            return this;
        }

        public Builder setDataFinal(Date dataFinal) {
            cerere.setDataFinal(dataFinal);
            return this;
        }

        public Builder setUserID(int userID) {
            cerere.setUserID(userID);
            return this;
        }

        public Application build() {
            return cerere;
        }

    }

}
