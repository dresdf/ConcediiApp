package com.jademy.concediiapp.helper;

import com.jademy.concediiapp.model.User;
import com.jademy.concediiapp.model.Cerere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;

public class DbUtils {

    AdminDbCred adminDB = AdminDbCred.getInstance();
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;

    //open a connection to the database
    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + adminDB.getDbHost() + ":" + adminDB.getDbPort() + "/"
                    + adminDB.getDb() + "?user=" + adminDB.getDbUsername() + "&password=" + adminDB.getDbPassword());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("DbUtils.openConnection() failed!*******************************************************");
            e.printStackTrace();
        }
    }

    //check credentials against the database.returns  User object
    public User checklogin(String username, String password) {
        User userResult = new User(-1);
        openConnection();
        String sqlQuerry = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            statement = conn.prepareStatement(sqlQuerry);
            statement.setString(1, username);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                //user exists. return User object 
                userResult = new User.Builder().setUserID(rs.getInt("userid"))
                        .setFirstName(rs.getString("firstname"))
                        .setLastName(rs.getString("lastname"))
                        .setEmail(rs.getString("email"))
                        .setPoza(getPoza(rs.getString("poza")))
                        .setRegDate(new java.util.Date(rs.getDate("regdate").getTime()))
                        .setRank(rs.getString("rank")).build();
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException("checkLogin failed" + ex);
        }
        return userResult;
    }

    public User createAccount(String prenume, String nume, String email, String username, String password, Date date) {
        User currentUser = null;
        openConnection();

        try {
            statement = conn.prepareStatement("SELECT * FROM users WHERE username=?");
            statement.setString(1, username);
            rs = statement.executeQuery();
            if (!rs.next()) {//username not in use. creation can begin
                String sql = "INSERT INTO users(firstname, lastname, email, username, password, regdate, rank) VALUES(?,?,?,?,?,?,'user')";
                //insert new user
                statement = conn.prepareStatement(sql);
                statement.setString(1, prenume);
                statement.setString(2, nume);
                statement.setString(3, email);
                statement.setString(4, username);
                statement.setString(5, password);
                statement.setDate(6, new java.sql.Date(date.getTime()));
                int flag = statement.executeUpdate();

                if (flag == 1) {
                    //get new user and return it
                    sql = "SELECT * FROM users WHERE username=?";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, username);
                    rs = statement.executeQuery();
                    rs.next();
                    currentUser = new User.Builder().setUserID(rs.getInt("id"))
                            .setFirstName(rs.getString("firstname"))
                            .setLastName(rs.getString("lastname"))
                            .setEmail(rs.getString("email"))
                            .setPoza(rs.getString("poza"))
                            .setRegDate(new Date(rs.getDate("regdate").getTime()))
                            .setRank(rs.getString("rank")).build();
                } else {
                    //insert failed. return empty user
                    currentUser = new User(-1);
                }
                rs.close();
                statement.close();
                conn.close();
            } else {//username exists. attempt login
                rs.close();
                statement.close();
                conn.close();
                currentUser = checklogin(username, password);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("createAccount failed " + ex);
        }

        return currentUser;
    }

    public List retrieveCereri(User currentUser) {
        openConnection();
        List<Cerere> resultList = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE userid=?";
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, currentUser.getUserID());
            rs = statement.executeQuery();

            while (rs.next()) {
                Cerere crr = new Cerere.Builder().setID(rs.getInt("requestid"))
                        .setTipConcediu(rs.getString("tipconcediu"))
                        .setDuration(rs.getInt("duration"))
                        .setStatus(rs.getString("status"))
                        .setDataStart(new Date(rs.getDate("datastart").getTime()))
                        .setDataFinal(new Date(rs.getDate("datafinal").getTime())).build();
                resultList.add(crr);
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultList;
    }

    public boolean recordCereri(Cerere cerere) {
        openConnection();
        String sql = "INSERT INTO requests(tipconcediu,datastart,datafinal,duration,status) VALUES(?,?,?,?,?)";

        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, cerere.getTipConcediu());
            statement.setDate(2, new java.sql.Date(cerere.getDataStart().getTime()));
            statement.setDate(3, new java.sql.Date(cerere.getDataFinal().getTime()));
            statement.setInt(4, cerere.getDuration());
            statement.setString(5, cerere.getStatus());

            return (statement.executeUpdate() > 0);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List retrieveAprovalPending(User currentUser) {
        openConnection();
        List<Cerere> resultList = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE userid=? AND status=?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, currentUser.getUserID());
            statement.setString(2, "INITIATA");

            rs = statement.executeQuery();
            while (rs.next()) {
                Cerere crr = new Cerere.Builder().setID(rs.getInt("cerereid"))
                        .setTipConcediu(rs.getString("tipconcediu"))
                        .setDuration(rs.getInt("duration"))
                        .setStatus(rs.getString("status"))
                        .setDataStart(new Date(rs.getDate("datastart").getTime()))
                        .setDataFinal(new Date(rs.getDate("datafinal").getTime())).build();
                resultList.add(crr);
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultList;
    }

    public boolean aproveOrDenyCerere(String hiddenid, String hiddenidreject) {
        String status = " ";
        int requestID = 0;

        if (!"".equals(hiddenid)) {
            status = "APROBATA";
            requestID = Integer.parseInt(hiddenid.trim());
        } else {
            status = "RESPINSA";
            requestID = Integer.parseInt(hiddenidreject.trim());
        }

        String sql = "UPDATE requests SET status=? WHERE requestid=?";

        try {
            openConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, requestID);

            return statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public DefaultPieDataset showPieChart(User currentuser) {

//        rs = createStatement().executeQuery("SELECT tipconcediu, sum(nrzile) AS total FROM prj_cereri WHERE uname='" + currentuser.getUsername() + "' " + "GROUP BY tipconcediu");
        DefaultPieDataset resultDefaultPie = new DefaultPieDataset();
        String sql = "SELECT tipconcediu, sum(duration) AS total FROM requests WHERE userid=? GROUP BY tipconcediu";
        try {
            openConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, currentuser.getUserID());
            rs = statement.executeQuery();
            while (rs.next()) {
                resultDefaultPie.setValue(rs.getString("tipconcediu"), rs.getInt("total"));
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultDefaultPie;
    }

    //checks if user has a profile pic. if not, load a default image
    public String getPoza(String poza) {
        if (poza == null || poza.equals("")) {
            return "default-avatar.jpg";
        } else {
            return poza;
        }
    }
}//end of class

