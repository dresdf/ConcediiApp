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

    public int getUserID() {
        return userID;
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

    public String getPoza() {
        return poza;
    }

    public String getRank() {
        return rank;
    }

    public Date getRegDate() {
        return regDate;
    }

    private User() {
        //empty
    }

    //user for login check
    public User(int ID) {
        this.userID = ID;
    }

    //contains all setters
    public static class Builder {

        User user = new User();

        public Builder setUserID(int userID) {
            user.userID = userID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setPoza(String poza) {
            user.poza = poza;
            return this;
        }

        public Builder setRank(String rank) {
            user.rank = rank;
            return this;
        }
        
        public Builder setRegDate(Date regDate) {
            user.regDate = regDate;
            return this;
        }

        public User build() {
            return user;
        }

    }

}//end of class

