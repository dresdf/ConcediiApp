//package com.jademy.concediiapp;
//
//import com.jademy.concediiapp.helper.Cerere;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CerereServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        DbUtils dbu = new DbUtils();
//        String message = "";
//
//        String firstName = request.getParameter("first_name").trim();
//        String lastName = request.getParameter("last_name").trim();
//        String username = request.getParameter("id").trim();
//        String email = request.getParameter("email").trim();
//        String tipConcediu = request.getParameter("dropdown");
//        String password = request.getParameter("id");
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date DataStart = null;
//        Date DataFinal = null;
//        try {
//            DataStart = sdf.parse(request.getParameter("datastart"));
//            DataFinal = sdf.parse(request.getParameter("datafinal"));
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//        long NrZile = ((DataFinal.getTime() - DataStart.getTime())) / (1000 * 60 * 60 * 24) + 1;
//
//        Cerere crr = new Cerere(firstName, lastName, email, username, password, tipConcediu, Long.toString(NrZile), "INITIATA", new java.sql.Date(DataStart.getTime()), new java.sql.Date(DataFinal.getTime()));
//        boolean flag = dbu.recordCereri(crr);
//
//        if (flag) {
//            message = "Cererea de concediu a fost inregistrata cu succes";
//        } else {
//            message = "Eroare la inregistrarea cererii de concediu in baza de date. Back to work!";
//        }
//            request.setAttribute("message", message);
//            request.getRequestDispatcher("/DashBoard").forward(request, response);
//        
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
