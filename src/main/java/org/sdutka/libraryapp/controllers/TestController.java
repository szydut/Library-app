package org.sdutka.libraryapp.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.User;
import org.sdutka.libraryapp.services.IBookService;
import org.sdutka.libraryapp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "/book/setUp", method = RequestMethod.PUT)
    public ResponseEntity<Object> bookInit() {
        this.bookService.setUp();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(path = "/user/setUp", method = RequestMethod.PUT)
    public ResponseEntity<Object> userInit() {
        this.userService.setUp();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}