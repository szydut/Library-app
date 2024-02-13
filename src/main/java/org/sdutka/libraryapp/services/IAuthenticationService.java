package org.sdutka.libraryapp.services;

public interface IAuthenticationService {
    void login(String login, String password);
    void logout();
}