package com.jademy.concediiapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

public class DbUtils {

    public Connection conn = null;
    public Statement statement = null;

    public static boolean checkSession(HttpServletRequest request) {

        //check if the session contains a logged user
        Object currentuser = request.getSession(true).getAttribute("curentuser");
        if (currentuser == null) {
            return false;
        }
        //TODO: check if this is stil valid for the project
        if (((User) currentuser).getID() == -1) {
            return false;
        }
        return true;
    }
    
    //open a connection and create a statement object
    public void openConnection(String database, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "?user=" + username + "&password=" + password);
            statement = conn.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("openConnection() failed!*******************************************************");
            e.printStackTrace();
        }
    }
    
     public User checklogin(String username, String password){
         //TODO: implement login. use try{}catch
         return null;
     }

}//end of class
