package org.sdutka.libraryapp.controllers;

import org.sdutka.libraryapp.services.IBookService;
import org.sdutka.libraryapp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "setUp", method =  RequestMethod.GET)
    public String addDataToDb() {
        this.bookService.setUp();
        this.userService.setUp();
        return "redirect:/home";
    }
}