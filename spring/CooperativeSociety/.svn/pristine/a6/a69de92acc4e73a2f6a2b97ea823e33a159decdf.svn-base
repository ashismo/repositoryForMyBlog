package com.org.coop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.coop.canonical.beans.UserProfile;
import com.org.coop.constants.WebConstants;
 
@Controller
public class LoginController {
 
	private static final Logger log = Logger.getLogger(LoginController.class); 
	
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to Cooperative society. ");
        return "welcome";
    }
 
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "admin";
//    }
 
//    @RequestMapping(value = "/db", method = RequestMethod.GET)
//    public String dbaPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "dba";
//    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
       public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null){    
             new SecurityContextLogoutHandler().logout(request, response, auth);
          }
          return "welcome";
       }
 
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
    
    @RequestMapping(value = "/otp", method = RequestMethod.GET)
    public String loginSuccess(ModelMap model) {
        model.addAttribute("userProfile", new UserProfile());
        return "otp";
    }
    
    @RequestMapping(value = "/verifyOTP", method = RequestMethod.POST)
    public String verifyOTP(@ModelAttribute("userProfile") UserProfile userProfile, 
    			HttpServletRequest request,
    			HttpServletResponse response) {
        System.out.println(userProfile.getOtp());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        String userName = auth.getName();
        userProfile.setUserName(userName);
        userProfile.setOtpMatch(true);
        userProfile.setOtpEnabled(true);
        request.getSession().setAttribute("userProfile", userProfile);
        String redirectUrl = WebConstants.DASH_BOARD_URL;
        return "redirect:" + redirectUrl;
    }
     
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
