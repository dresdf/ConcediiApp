package com.jademy.concediiapp.model;

import java.util.Date;

public class Cerere {

    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String tipConcediu;
    private String nrZile;
    private String status;
    private Date dataStart;
    private Date dataFinal;

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTipConcediu() {
        return tipConcediu;
    }

    public String getNrZile() {
        return nrZile;
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

    private Cerere() {

    }

    //contains all setters
    public static class Builder {

        Cerere cerere = new Cerere();

        public Builder setID(int ID) {
            cerere.ID = ID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            cerere.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            cerere.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            cerere.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            cerere.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            cerere.password = password;
            return this;
        }

        public Builder setTipConcediu(String tipConcediu) {
            cerere.tipConcediu = tipConcediu;
            return this;
        }

        public Builder setNrZile(String nrZile) {
            cerere.nrZile = nrZile;
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

        public Cerere build() {
            return cerere;
        }

    }

}
