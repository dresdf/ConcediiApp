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

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipConcediu() {
        return tipConcediu;
    }

    public void setTipConcediu(String tipConcediu) {
        this.tipConcediu = tipConcediu;
    }

    public String getNrZile() {
        return nrZile;
    }

    public void setNrZile(String nrZile) {
        this.nrZile = nrZile;
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

    public Cerere(int ID, String firstName, String lastName, String email, String username, String password, String tipConcediu, String nrZile, String status, Date dataStart, Date dataFinal) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tipConcediu = tipConcediu;
        this.nrZile = nrZile;
        this.status = status;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
    }

    public Cerere(String firstName, String lastName, String email, String username, String password, String tipConcediu, String nrZile, String status, Date dataStart, Date dataFinal) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tipConcediu = tipConcediu;
        this.nrZile = nrZile;
        this.status = status;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
    }

}
