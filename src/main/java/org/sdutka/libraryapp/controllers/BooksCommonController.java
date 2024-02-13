package org.sdutka.libraryapp.controllers;

import jakarta.annotation.Resource;
import org.sdutka.libraryapp.dao.IBookDAO;
import org.sdutka.libraryapp.model.Book;
import org.sdutka.libraryapp.services.IBookService;
import org.sdutka.libraryapp.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BooksCommonController {

    @Autowired
    IBookService bookService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String main(Model model){
        model.addAttribute("listBooks", bookService.getAll());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "home";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact(Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "contact";
    }

    @RequestMapping(path = "/addNewBook", method = RequestMethod.GET)
    public String addNewBook(Model model){
        model.addAttribute("newBook", new Book());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "addNewBook";
    }

    @RequestMapping(path = "/addNewBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book, Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        bookService.persist(book);
        return "redirect:/home";
    }

    @RequestMapping(path = "/bookSearch", method = RequestMethod.GET)
    public String bookSearch(Model model){
        model.addAttribute("listBooks", bookService.getAll());
        model.addAttribute("searchingPhrase", "");
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "bookSearch";
    }

    @RequestMapping(path = "/bookSearch", method = RequestMethod.POST)
    public String bookSearch(@RequestParam("searchingPhrase") String searchingPhrase, Model model){
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("listBooks", bookService.getByPattern(searchingPhrase));
        return "bookSearch";
    }
}
