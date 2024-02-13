package org.sdutka.libraryapp.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    @Autowired
    IUserDAO userDAO;

    @RequestMapping(path = "/test/addUser", method = RequestMethod.GET)
    public String addUser(){
        User user = new User();
        user.setLogin("admin");
        user.setPassword(DigestUtils.md5Hex("admin123"));
        user.setName("admin");
        user.setSurname("test");

        this.userDAO.persist(user);
        return "redirect:/home";
    }
}
