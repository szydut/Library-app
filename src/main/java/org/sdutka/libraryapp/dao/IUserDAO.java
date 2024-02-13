package org.sdutka.libraryapp.dao;

import org.sdutka.libraryapp.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getById(int id);
    Optional<User> getByLogin(String login);
    List<User> getAll();
    void persist(User user);
}
