package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.helper.DbUtils;
import com.jademy.concediiapp.model.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dragos Secara
 */
@Controller
public class CreateMainController {

    DbUtils dbu = new DbUtils();

    @RequestMapping("/main")
    public ModelAndView showMain(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("main");

        User currentUser = (User) request.getSession().getAttribute("currentuser");
        String message = (String)request.getAttribute("message");
        List cereri = null;
        List pending = null;
        try {
            cereri = dbu.retrieveCereri(currentUser);
            pending = dbu.retrieveAprovalPending(currentUser);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        mav.addObject("listacereri", cereri);
        mav.addObject("listapending", pending);
        mav.addObject("message", message);
        return mav;
    }
}
