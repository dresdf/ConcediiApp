package com.jademy.concediiapp.helper;

import com.jademy.concediiapp.model.User;
import com.jademy.concediiapp.model.Cerere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class DbUtils {

    AdminDbCred adminDB = AdminDbCred.getInstance();

    //open a connection and create a statement object
    public Statement createStatement() {
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + adminDB.getDb() + "?user=" + adminDB.getDbUsername() + "&password=" + adminDB.getDbPassword());
            statement = conn.createStatement();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("DbUtils.openConnection() failed!*******************************************************");
            e.printStackTrace();
        }
        return statement;
    }

    public User checklogin(String username, String password) throws SQLException {
        String table = "prj_members";
        ResultSet result = createStatement().executeQuery("SELECT * FROM " + table + " WHERE uname='" + username + "' AND pass='" + password + "'");
        if (result.next()) {
            //user exists. return User object 
            return new User.Builder().setID(result.getInt("id"))
                    .setUsername(result.getString("uname"))
                    .setPassword(result.getString("pass"))
                    .setFirstName(result.getString("first_name"))
                    .setLastName(result.getString("last_name"))
                    .setEmail(result.getString("email"))
                    .setPoza(result.getString("poza")).build();
        } else {
            //user does not exist. return empty User
            return new User(-1);
        }
    }

    public User createAccount(String prenume, String nume, String email, String username, String password, String date) throws SQLException {
//TODO: uncomment for full functionality
//        String sql = "INSERT INTO prj_members(first_name, last_name, email, uname, pass, regdate, poza) "
//                + "values('" + prenume + "','" + nume + "','" + email + "','" + username + "','" + password + "','" + date + "','default.jpg')";
//
//        createStatement().executeUpdate(sql);
        ResultSet result = createStatement().executeQuery("SELECT * FROM prj_members WHERE id=(SELECT MAX(id) FROM prj_members)");
        result.next();
        return new User.Builder().setID(result.getInt("id"))
                .setUsername(result.getString("uname"))
                .setPassword(result.getString("pass"))
                .setFirstName(result.getString("first_name"))
                .setLastName(result.getString("last_name"))
                .setEmail(result.getString("email"))
                .setPoza(result.getString("poza")).build();

    }

    public List retrieveCereri(User currentUser) throws SQLException {
        List<Cerere> result = new ArrayList<>();

        ResultSet rs = createStatement().executeQuery("SELECT * FROM prj_cereri WHERE uname='" + currentUser.getUsername() + "'");

        while (rs.next()) {
            Cerere crr = new Cerere(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("uname"), rs.getString("pass"), rs.getString("tipconcediu"), rs.getString("nrzile"), rs.getString("status"), rs.getDate("datastart"), rs.getDate("datafinal"));
            result.add(crr);
        }
        return result;
    }

    public boolean recordCereri(Cerere cerere) {
        String sql = "INSERT INTO prj_cereri(first_name,last_name,email,uname,tipconcediu,pass,datastart,datafinal,nrzile,status) "
                + "VALUES('" + cerere.getFirstName() + "','" + cerere.getLastName() + "','" + cerere.getEmail() + "','" + cerere.getUsername() + "','" + cerere.getTipConcediu() + "','" + cerere.getPassword() + "','" + cerere.getDataStart() + "','" + cerere.getDataFinal() + "','" + cerere.getNrZile() + "','" + cerere.getStatus() + "')";

        try {
            createStatement().executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List retrieveAprovalPending(User currentUser) throws SQLException {
        List<Cerere> result = new ArrayList<>();

        ResultSet rs = createStatement().executeQuery("SELECT * FROM prj_cereri WHERE uname='" + currentUser.getUsername() + "' AND status='INITIATA'");

        while (rs.next()) {
            Cerere crr = new Cerere(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("uname"), rs.getString("pass"), rs.getString("tipconcediu"), rs.getString("nrzile"), rs.getString("status"), rs.getDate("datastart"), rs.getDate("datafinal"));
            result.add(crr);
        }
        return result;
    }

    public boolean aproveOrDenyCerere(String hiddenid, String hiddenidreject) {
        String status = " ";
        String UserId = " ";

        if (!"".equals(hiddenid)) {
            status = "APROBATA";
            UserId = hiddenid.trim();
        } else {
            status = "RESPINSA";
            UserId = hiddenidreject.trim();
        }

        String sql = "UPDATE prj_cereri SET status='" + status + "' WHERE id='" + UserId + "'";

        try {
            createStatement().executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public DefaultPieDataset showPieChart(User currentuser) throws SQLException {
        ResultSet resultSet = createStatement().executeQuery("SELECT tipconcediu, sum(nrzile) AS total FROM prj_cereri WHERE uname='" + currentuser.getUsername() + "' " + "GROUP BY tipconcediu");

        DefaultPieDataset resultDefaultPie = new DefaultPieDataset();
        while (resultSet.next()) {
            resultDefaultPie.setValue(resultSet.getString("tipconcediu"), Double.parseDouble(resultSet.getString("total")));
        }
        return resultDefaultPie;
    }

    public String getPoza(User currentUser, String username) throws SQLException {
        ResultSet result = createStatement().executeQuery("SELECT * FROM prj_members WHERE uname='" + username + "'");

        if (result.next()) {
            return result.getString("poza");
        } else {
            return "default-avatar.jpg";
        }
    }
}//end of class

