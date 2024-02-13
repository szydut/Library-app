package org.sdutka.libraryapp.services;

import org.sdutka.libraryapp.model.User;

public interface IUserService {
    void persist(User user);
    boolean userExist(String login);
    void setUp();
}