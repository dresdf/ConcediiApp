package com.jademy.concediiapp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Application {

    @Id
    @Column(name = "requestid", nullable = false)
    private int requestID;

    @Column(name = "tipconcediu", nullable = false)
    private String tipConcediu;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "datastart", nullable = false)
    private Date dataStart;

    @Column(name = "datafinal", nullable = false)
    private Date dataFinal;

    @Column(name = "userid", nullable = false)
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
        //empty for Hibernate
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
