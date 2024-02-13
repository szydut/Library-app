package org.sdutka.libraryapp.controllers;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.User;
import org.sdutka.libraryapp.services.IAuthenticationService;
import org.sdutka.libraryapp.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    public static User loggedUser;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("userObject", new User());
        model.addAttribute("isLogged", AuthenticationController.loggedUser != null);
        System.out.println("Auth");
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user){
        this.authenticationService.login(user.getLogin(), user.getPassword());
        if(this.sessionObject.isLogged()){
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/home";
    }
}

