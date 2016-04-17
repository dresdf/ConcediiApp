package com.jademy.concediiapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginServlet extends HttpServlet {
    AdminDbCred adminDB = AdminDbCred.getInstance();
    DbUtils dbu = new DbUtils();
    User currentUser = null;

    String user_username_from_DB = "";
    String user_password_from_DB = "";
    String user_name_from_DB = "";
    String user_last_from_DB = "";
    String user_email_from_DB = "";

    Connection conn = null;
    Statement statement = null;
    ResultSet result = null;

    String db = adminDB.getDb();
    String dbUser = adminDB.getDbUsername();
    String dbPass = adminDB.getDbPassword();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/" + db;
            conn = DriverManager.getConnection(url, dbUser, dbPass);
            if (!conn.isValid(0)) {
                System.out.println("conexiunea a esuat");
            } else {
                System.out.println("conectare cu succes la baza de date****************************************** ");

                statement = conn.createStatement();
                result = statement.executeQuery("select * from prj_members where uname = '" + user_id + "'");

                if (result.isBeforeFirst()) {
                    while (result.next()) {
                        user_username_from_DB = result.getString("uname").trim();
                        user_password_from_DB = result.getString("pass").trim();
                        user_name_from_DB = result.getString("first_name").trim();
                        user_last_from_DB = result.getString("last_name").trim();
                        user_email_from_DB = result.getString("email").trim();

                        System.out.println(user_username_from_DB + " / " + user_password_from_DB);
                    }
                    if (user_id.equals(user_username_from_DB) && password.equals(user_password_from_DB)) {
                        request.setAttribute("id", user_username_from_DB);
                        request.setAttribute("first_name", user_name_from_DB);
                        request.setAttribute("last_name", user_last_from_DB);
                        request.setAttribute("email", user_email_from_DB);

                        HttpSession s = request.getSession();
                        s.setAttribute("userid", user_username_from_DB);
                        s.setAttribute("first_name", user_name_from_DB);
                        s.setAttribute("last_name", user_last_from_DB);
                        s.setAttribute("email", user_email_from_DB);
                        System.out.println(user_username_from_DB + " / " + user_password_from_DB);

                        request.getRequestDispatcher("/succes.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/fail.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/fail.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | ServletException | IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                result.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
