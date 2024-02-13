package org.sdutka.libraryapp.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IBookDAO;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.Book;
import org.sdutka.libraryapp.model.User;
import org.sdutka.libraryapp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserDAO userDAO;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void persist(User user) {
        this.userDAO.persist(user);
    }

    @Override
    public boolean userExist(String login) {
        if(userDAO.getByLogin(login).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public void setUp() {
        List<User> users = new ArrayList<>();
        users.add(new User("admin", DigestUtils.md5Hex("admin123"), "admin","admin" ));
        users.add(new User("janusz", DigestUtils.md5Hex("janusz123"), "janusz", "kowalski"));

        for(User user : users){
            this.userDAO.persist(user);
        }
    }
}
