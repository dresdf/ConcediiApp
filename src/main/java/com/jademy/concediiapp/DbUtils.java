package com.jademy.concediiapp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
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

    //load properties file with database connection details
    public HashMap<String, String> loadDbCredential() {
        HashMap<String, String> result = new HashMap<>();
        try {
            //load the properties file
            Properties props = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("database.properties");
            props.load(input);

            result.put("user", props.getProperty("dbuser"));
            result.put("pass", props.getProperty("dbpassword"));
            result.put("db", props.getProperty("db"));
        } catch (Exception e) {
            System.out.println("loadDbCredential() failed!*****************************************************");
            e.printStackTrace();
        }

        return result;
    }

    //open a connection and create a statement object
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + loadDbCredential().get("db") + "?user=" + loadDbCredential().get("user") + "&password=" + loadDbCredential().get("pass"));
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
