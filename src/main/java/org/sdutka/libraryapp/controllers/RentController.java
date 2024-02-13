package org.sdutka.libraryapp.controllers;

import jakarta.annotation.Resource;
import org.sdutka.libraryapp.services.IBookService;
import org.sdutka.libraryapp.services.IRentService;
import org.sdutka.libraryapp.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RentController {

    @Autowired
    IRentService rentService;

    @Autowired
    IBookService bookService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path="/rent/{bookId}", method = RequestMethod.GET)
    public String rent(Model model, @PathVariable int bookId){
        this.rentService.persist(bookId);
        return "redirect:/home";
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public String account(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("listRentedBooks", rentService.getAll());
        return "account";
    }

    @RequestMapping(path = "/account/rented", method = RequestMethod.GET)
    public String userRent(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("userRentedBooks", rentService.getAllById(sessionObject.getLoggedUser()));
        return "account/rented";
    }

    @RequestMapping(path="/account/overdue", method = RequestMethod.GET)
    public String rentOverdue(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("bookOverdue", rentService.getAllOverdue());
        return "account/overdue";
    }

    @RequestMapping(path = "account/return/{rentId}", method = RequestMethod.GET)
    public String returnBook(@PathVariable final int rentId){
        rentService.bookReturn(rentId);
        return "redirect:/account/rented";
    }
}
