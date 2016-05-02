package com.jademy.concediiapp.model;

import java.util.Date;

public class User {

    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String poza;
    private String rank;
    private Date regDate;
    private String username;
    private String password;

    private User() {
        //empty 
    }

    //user for login check
    public User(int ID) {
        this.userID = ID;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
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

//</editor-fold>
    //builder for User
    public static class UserBuilder {

        User user = new User();

        public UserBuilder setUserID(int userID) {
            user.setUserID(userID);
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setPoza(String poza) {
            user.setPoza(poza);
            return this;
        }

        public UserBuilder setRank(String rank) {
            user.setRank(rank);
            return this;
        }

        public UserBuilder setRegDate(Date regDate) {
            user.setRegDate(regDate);
            return this;
        }

        public UserBuilder setUsername(String username) {
            user.setUsername(username);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            return user;
        }

    }

}//end of class

