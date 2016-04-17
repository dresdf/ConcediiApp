package com.jademy.concediiapp;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDbCred adminDB = AdminDbCred.getInstance();
        DbUtils dbu = new DbUtils();
        User currentUser;

        String firstName = request.getParameter("first_name").trim();
        String lastName = request.getParameter("last_name").trim();
        String email = request.getParameter("email").trim();
        String username = request.getParameter("uname").trim();
        String password = request.getParameter("pass").trim();
        String date = request.getParameter("datastart");

        try {
            currentUser = dbu.createAccount(firstName, lastName, email, username, password, date, adminDB.getDb(), adminDB.getDbUsername(), adminDB.getDbPassword());

            HttpSession s = request.getSession();
            s.setAttribute("currentuser", currentUser);
            s.setAttribute("dbcred", adminDB);
            
            request.getRequestDispatcher("/succes.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("first_name", firstName);
            request.setAttribute("last_name", lastName);
            request.setAttribute("email", email);
            request.setAttribute("uname", username);
            request.setAttribute("pass", password);
            request.setAttribute("message", "Eroare la inregistrarea utilizatorului in baza de date");

            request.getRequestDispatcher("/register.jsp").forward(request, response);
            e.printStackTrace();
        }

    }
}
