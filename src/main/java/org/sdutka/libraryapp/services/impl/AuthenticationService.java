package org.sdutka.libraryapp.services.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.User;
import org.sdutka.libraryapp.services.IAuthenticationService;
import org.sdutka.libraryapp.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void login(String login, String password) {
        Optional<User> userBox = this.userDAO.getByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))){
            this.sessionObject.setLoggedUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}
