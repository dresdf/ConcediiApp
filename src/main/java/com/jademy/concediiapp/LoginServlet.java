package com.jademy.concediiapp;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    AdminDbCred adminDB = AdminDbCred.getInstance();
    DbUtils dbu = new DbUtils();
    User currentUser = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //using POST
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();

        try {
            currentUser = dbu.checklogin(adminDB.getDb(), adminDB.getDbUsername(), adminDB.getDbPassword(), user_id, password);

            if (currentUser.getID() != -1) {
                HttpSession s = request.getSession();
                s.setAttribute("currentuser", currentUser);
                s.setAttribute("dbcred", adminDB);
                request.getRequestDispatcher("/succes.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Utilizatorul sau parola sunt incorecte!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
