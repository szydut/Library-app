package org.sdutka.libraryapp.dao.impl.memory;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.sdutka.libraryapp.dao.IUserDAO;
import org.sdutka.libraryapp.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
//        this.users.add(new User(1, "admin", DigestUtils.md5Hex("admin123")));
//        this.users.add(new User(2, "janusz", DigestUtils.md5Hex("janusz123")));
    }

    @Override
    public Optional<User> getById(final int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Optional<User> getByLogin(final String login) {
        return this.users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void persist(final User user) {
        this.users.stream()
                .map(User::getId)
                .max(Integer::compare)
                .ifPresentOrElse(
                        i -> user.setId(i + 1),
                        () -> user.setId(1)
                );
        this.users.add(user);
    }
}
