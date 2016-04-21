package com.jademy.concediiapp;

public class User {

    private int ID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String poza;

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    private User() {
        //empty
    }
    
    //user for login check
    public User(int ID) {
        this.ID = ID;
    }

    //contains all setters
    public static class Builder {

        User user = new User();

        public Builder setID(int ID) {
            user.ID = ID;
            return this;
        }

        public Builder setUsername(String username) {
            user.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
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
        
        public  User build(){
            return user;
        }

    }

}//end of class

