package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.helper.DbUtils;
import com.jademy.concediiapp.model.User;
import java.util.List;
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
        ModelAndView mav = new ModelAndView();
        User currentUser = (User) request.getSession().getAttribute("currentuser");

        if (currentUser.getRank().equals("admin")) {
            String message = (String) request.getAttribute("message");
            List cereri = null;
            List pending = null;
            List allPending = null;

            cereri = dbu.retrieveCereri(currentUser);
            pending = dbu.retrieveAprovalPending(currentUser);
            allPending = dbu.retrieveAllAprovalPending();

            mav.setViewName("adminMain");
            mav.addObject("listacereri", cereri);
            mav.addObject("listapending", pending);
            mav.addObject("listaaprobare", allPending);
            mav.addObject("message", message);
        } else {
            String message = (String) request.getAttribute("message");
            List cereri = null;
            List pending = null;

            cereri = dbu.retrieveCereri(currentUser);
            pending = dbu.retrieveAprovalPending(currentUser);
            
            mav.setViewName("userMain");
            mav.addObject("listacereri", cereri);
            mav.addObject("listapending", pending);
            mav.addObject("message", message);
        }

        return mav;
    }
}
