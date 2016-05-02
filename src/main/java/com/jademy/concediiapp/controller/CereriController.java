package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.helper.DbUtils;
import com.jademy.concediiapp.model.Application;
import com.jademy.concediiapp.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ModelAndView adaugaCerere(String dropdown, String datastart, String datafinal, HttpServletRequest request) {
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
        long duration = ((DataFinal.getTime() - DataStart.getTime())) / (1000 * 60 * 60 * 24) + 1;

        Application crr = new Application.Builder().setTipConcediu(tipConcediu)
                .setDuration((int)duration)
                .setStatus("INITIATA")
                .setDataStart(new java.sql.Date(DataStart.getTime()))
                .setDataFinal(new java.sql.Date(DataFinal.getTime())).build();
        boolean flag = dbu.recordCereri(crr);

        if (flag) {
            message = "Cererea de concediu a fost inregistrata cu succes";
        } else {
            message = "Eroare la inregistrarea cererii de concediu in baza de date.";
        }

        mav = new ModelAndView("forward:/main");
        request.setAttribute("mssage", message);

        return mav;
    }

    @RequestMapping(value = "/aproba", method = RequestMethod.POST)
    public ModelAndView aprobareCerere(String hiddenid, String hiddenidreject, HttpServletRequest request) {
        String message;

        dbu.aproveOrDenyCerere(hiddenid, hiddenidreject);

        mav = new ModelAndView("forward:/main");

        return mav;
    }

}
