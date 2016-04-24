package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.helper.DbUtils;
import com.jademy.concediiapp.model.Cerere;
import com.jademy.concediiapp.model.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dragos Secara
 */
@Controller
@RequestMapping("/cerere")
public class CereriController {

    ModelAndView mav = null;
    DbUtils dbu = new DbUtils();

    @RequestMapping(value = "/adauga", method = RequestMethod.POST)
    public void adaugaCerere(String dropdown, String datastart, String datafinal, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("currentuser");
        String message = " ";
        String tipConcediu = dropdown;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date DataStart = null;
        Date DataFinal = null;
        try {
            DataStart = sdf.parse(datastart);
            DataFinal = sdf.parse(datafinal);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        long nrZile = ((DataFinal.getTime() - DataStart.getTime())) / (1000 * 60 * 60 * 24) + 1;

        Cerere crr = new Cerere.Builder().setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setTipConcediu(tipConcediu)
                .setNrZile(Long.toString(nrZile))
                .setStatus("INITIATA")
                .setDataStart(new java.sql.Date(DataStart.getTime()))
                .setDataFinal(new java.sql.Date(DataFinal.getTime())).build();
        boolean flag = dbu.recordCereri(crr);

        if (flag) {
            message = "Cererea de concediu a fost inregistrata cu succes";
        } else {
            message = "Eroare la inregistrarea cererii de concediu in baza de date.";
        }
        List cereri = null;
        List pending = null;
        try {
            cereri = dbu.retrieveCereri(user);
            pending = dbu.retrieveAprovalPending(user);
        } catch (SQLException ex) {
            Logger.getLogger(CereriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mav = new ModelAndView("main");
        mav.addObject("message", message);
        mav.addObject("listacereri", cereri);
        mav.addObject("pending", pending);
    }

    public void aprobareCerere() {

    }

}
