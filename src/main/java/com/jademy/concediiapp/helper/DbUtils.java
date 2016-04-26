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
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + adminDB.getDbHost() + ":" + adminDB.getDbPort() + "/"
                    + adminDB.getDb() + "?user=" + adminDB.getDbUsername() + "&password=" + adminDB.getDbPassword());
            statement = conn.createStatement();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("DbUtils.openConnection() failed!*******************************************************");
            e.printStackTrace();
        }
        return statement;
    }

    public User checklogin(String username, String password) throws SQLException {
        String table = "users";
        ResultSet result = createStatement().executeQuery("SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'");
        if (result.next()) {
            //user exists. return User object 
            return new User.Builder().setID(result.getInt("userid"))
                    .setFirstName(result.getString("firstname"))
                    .setLastName(result.getString("lastname"))
                    .setEmail(result.getString("email"))
                    .setPoza(getPoza(result.getString("poza")))
                    .setRank(result.getString("rank")).build();
        } else {
            //user does not exist. return empty User
            return new User(-1);
        }
    }

    public User createAccount(String prenume, String nume, String email, String username, String password, String date) throws SQLException {
        String sql = "INSERT INTO users(firstname, lastname, email, username, password, regdate, rank) "
                + "values('" + prenume + "','" + nume + "','" + email + "','" + username + "','" + password + "','" + date + "','user')";

        createStatement().executeUpdate(sql);
        ResultSet result = createStatement().executeQuery("SELECT * FROM users WHERE id=(SELECT MAX(id) FROM users)");
        result.next();
        return new User.Builder().setID(result.getInt("id"))
                .setFirstName(result.getString("firstname"))
                .setLastName(result.getString("lastname"))
                .setEmail(result.getString("email"))
                .setPoza(result.getString("poza"))
                .setRank(result.getString("rank")).build();

    }

    public List retrieveCereri(User currentUser) throws SQLException {
        List<Cerere> result = new ArrayList<>();

        //TODO: make a join for answer
        //SELECT 
        //* 
        //FROM
        //(SELECT * FROM users) t1 
        //INNER JOIN
        //(SELECT * FROM requests) t2
        //ON t1.userid = t2.userid
        ResultSet rs = createStatement().executeQuery("SELECT * FROM requests WHERE userid='" + currentUser.getID()+ "'");

        while (rs.next()) {
            Cerere crr = new Cerere.Builder().setID(rs.getInt("cerereid"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setUsername(rs.getString("uname"))
                    .setPassword(rs.getString("pass"))
                    .setEmail(rs.getString("email"))
                    .setTipConcediu(rs.getString("tipconcediu"))
                    .setNrZile(rs.getString("nrzile"))
                    .setStatus(rs.getString("status"))
                    .setDataStart(rs.getDate("datastart"))
                    .setDataFinal(rs.getDate("datafinal")).build();
            result.add(crr);
        }
        return result;
    }

    public boolean recordCereri(Cerere cerere) {
        String sql = "INSERT INTO requests(tipconcediu,pass,datastart,datafinal,nrzile,status) "
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
            Cerere crr = new Cerere.Builder().setID(rs.getInt("id"))
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setUsername(rs.getString("uname"))
                    .setPassword(rs.getString("pass"))
                    .setEmail(rs.getString("email"))
                    .setTipConcediu(rs.getString("tipconcediu"))
                    .setNrZile(rs.getString("nrzile"))
                    .setStatus(rs.getString("status"))
                    .setDataStart(rs.getDate("datastart"))
                    .setDataFinal(rs.getDate("datafinal")).build();
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

    //checks if user has a profile pic. if not, load a default image
    public String getPoza(String poza) {
        if (poza.equals("")) {
            return "default-avatar.jpg";
        } else {
            return poza;
        }
    }
}//end of class

