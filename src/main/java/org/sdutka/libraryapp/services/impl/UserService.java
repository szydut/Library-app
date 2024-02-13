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
        users.add(new User("admin", DigestUtils.md5Hex("admin123")));
        users.add(new User("janusz", DigestUtils.md5Hex("janusz123")));

        for(User user : users){
            this.userDAO.persist(user);
        }
        List<Book> books = new ArrayList<>();
        books.add(new Book(2, false, "Dave Thomas and Andy Hunt", "The Pragmatic Programmer: Your Journey to Mastery", "978-0-201-61622-4"));
        books.add(new Book(3, true, "Brian Christian and Tom Griffiths", "Algorithms to Live By: The Computer Science of Human Decisions", "978-0-06-234739-8"));
        books.add(new Book(4, true, "Gene Kim, Kevin Behr, and George Spafford", "The Phoenix Project: A Novel About IT, DevOps, and Helping Your Business Win", "978-0-9885208384"));
        books.add(new Book(5, true, "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein", "Introduction to Algorithms", "978-0262033848"));
        books.add(new Book(6, false, "Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides", "Design Patterns: Elements of Reusable Object-Oriented Software", "978-0201633610"));
        books.add(new Book(7, false, "Charles Petzold", "Code: The Hidden Language of Computer Hardware and Software", "978-0735611313"));
        books.add(new Book(8, false, "Yuval Noah Harari", "Sapiens: A Brief History of Humankind", "978-0062316110"));


        users.add(new User("admin", DigestUtils.md5Hex("admin123")));
        users.add(new User("janusz", DigestUtils.md5Hex("janusz123")));

        for(Book book : books){
            this.bookDAO.persist(book);
        }
    }
}
