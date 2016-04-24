package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.model.User;
import com.jademy.concediiapp.helper.DbUtils;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dragos Secara
 */
@Controller
@RequestMapping("/user")
public class UserController {

    ModelAndView mav = null;
    DbUtils dbu = new DbUtils();
    User currentUser = null;

    //login the user. if it fails, redirect to login page
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request) {

        String input_username = username.trim();
        String input_password = password.trim();

        try {
            currentUser = dbu.checklogin(input_username, input_password);

            if (currentUser.getID() != -1) {
                request.getSession().setAttribute("currentuser", currentUser);
                List cereri = dbu.retrieveCereri(currentUser);
                List pending = dbu.retrieveAprovalPending(currentUser);
                mav = new ModelAndView("main");
                mav.addObject("listacereri", cereri);
                mav.addObject("pending", pending);

            } else {
                mav = new ModelAndView("fail");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mav;
    }

    //redirect from login page to create account page
    @RequestMapping("/register")
    public String goToRegister() {
        return "register";
    }

    //create account and redirect to main. if it fails, redirect to create account page preserving input data, with fail message
    @RequestMapping("/doregister")
    public ModelAndView register(String first_name, String last_name, String email, String uname, String pass, String datastart) {
        String input_firstName = first_name.trim();
        String input_lastName = last_name.trim();
        String input_email = email.trim();
        String input_username = uname.trim();
        String input_password = pass.trim();
        String input_date = datastart;

        try {
            currentUser = dbu.createAccount(input_firstName, input_lastName, input_email, input_username, input_password, input_date);
            if (currentUser != null) {
                mav = new ModelAndView("registerend", "currentuser", currentUser);
            }else{
                mav = new ModelAndView("fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }

        return mav;
    }

   
}
