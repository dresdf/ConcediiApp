package com.jademy.concediiapp.controller;

import com.jademy.concediiapp.model.User;
import com.jademy.concediiapp.helper.DbUtils;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        currentUser = dbu.checklogin(input_username, input_password);

        if (currentUser.getUserID() != -1) {
            request.getSession().setAttribute("currentuser", currentUser);
            mav = new ModelAndView("forward:/main");
        } else {
            mav = new ModelAndView("fail");

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
    public ModelAndView register(String first_name, String last_name, String email, String uname, String pass, String datastart, HttpServletRequest request) {
        String input_firstName = first_name.trim();
        String input_lastName = last_name.trim();
        String input_email = email.trim();
        String input_username = uname.trim();
        String input_password = pass.trim();
        Date regDate = new Date();//get current time
//        try {
//            regDate = new SimpleDateFormat("yyyy-MM-dd").parse(input_date);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }

        currentUser = dbu.createAccount(input_firstName, input_lastName, input_email, input_username, input_password, regDate);
        if (currentUser != null) {
            request.getSession().setAttribute("currentuser", currentUser);
            mav = new ModelAndView("forward:/main");
        } else {
            mav = new ModelAndView("fail");
        }

        return mav;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("currentuser", null);
        return "forward:/";
    }
}
