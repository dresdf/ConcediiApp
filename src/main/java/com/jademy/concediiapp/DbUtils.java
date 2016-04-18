package com.jademy.concediiapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class DbUtils {

    public Connection conn = null;
    public Statement statement = null;
    AdminDbCred adminDB = AdminDbCred.getInstance();

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
    public void openConnection(String database, String dbUsername, String dbPassword) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database + "?user=" + dbUsername + "&password=" + dbPassword);
            statement = conn.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("DbUtils.openConnection() failed!*******************************************************");
            e.printStackTrace();
        }

    }

    public User checklogin(String database, String dbUsername, String dbPassword, String username, String password) throws SQLException {

        openConnection(adminDB.getDb(), adminDB.getDbUsername(), adminDB.getDbPassword());

        ResultSet result = statement.executeQuery("SELECT * FROM prj_members WHERE uname='" + username + "' AND pass='" + password + "'");

        if (result.next()) {
            //user exists. return User object 
            return new User(result.getInt("id"), result.getString("uname"), result.getString("pass"), result.getString("first_name"), result.getString("last_name"), result.getString("email"), result.getString("poza"));
        } else {
            //user does not exist. return empty User
            return new User();
        }
    }

    public User createAccount(String prenume, String nume, String email, String username, String password, String date, String database, String dbUsername, String dbPassword) throws SQLException {

        openConnection(database, dbUsername, dbPassword);
        String sql = "INSERT INTO prj_members(first_name, last_name, email, uname, pass, regdate, poza) "
                + "values('" + prenume + "','" + nume + "','" + email + "','" + username + "','" + password + "','" + date + "','default.jpg')";

        statement.executeUpdate(sql);

        ResultSet result = statement.executeQuery("SELECT * FROM prj_members WHERE id=(SELECT MAX(id) FROM prj_members)");

        return new User(result.getInt("id"), result.getString("uname"), result.getString("pass"), result.getString("first_name"), result.getString("last_name"), result.getString("email"), result.getString("poza"));

    }

    public List retrieveCereri(User currentUser) throws SQLException {
        List<Cerere> result = new ArrayList<>();
        openConnection(adminDB.getDb(), adminDB.getDbUsername(), adminDB.getDbPassword());

        ResultSet rs = statement.executeQuery("SELECT * FROM prj_cereri WHERE uname='" + currentUser.getUsername() + "'");

        while (rs.next()) {
            Cerere crr = new Cerere(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("uname"), rs.getString("pass"), rs.getString("tipconcediu"), rs.getString("nrzile"), rs.getString("status"), rs.getDate("datastart"), rs.getDate("datafinal"));
            result.add(crr);
        }
        return result;
    }

    public boolean recordCereri(Cerere cerere) {
        openConnection(adminDB.getDb(), adminDB.getDbUsername(), adminDB.getDbPassword());
        String sql = "INSERT INTO prj_cereri(first_name,last_name,email,uname,tipconcediu,pass,datastart,datafinal,nrzile,status) "
                + "VALUES('" + cerere.getFirstName() + "','" + cerere.getLastName() + "','" + cerere.getEmail() + "','" + cerere.getUsername() + "','" + cerere.getTipConcediu() + "','" + cerere.getPassword() + "','" + cerere.getDataStart() + "','" + cerere.getDataFinal() + "','" + cerere.getNrZile() + "','" + cerere.getStatus() + "')";

        try {
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}//end of class

