package org.sdutka.libraryapp.services.impl;

import org.sdutka.libraryapp.dao.IBookDAO;
import org.sdutka.libraryapp.model.Book;
import org.sdutka.libraryapp.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    IBookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return bookDAO.getByPattern(pattern);
    }

    @Override
    public void persist(Book book) {
        book.setRentStatus(false);
        this.bookDAO.persist(book);
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void setUp() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(false, "Dave Thomas and Andy Hunt", "The Pragmatic Programmer: Your Journey to Mastery", "978-0-201-61622-4"));
        books.add(new Book(false, "Brian Christian and Tom Griffiths", "Algorithms to Live By: The Computer Science of Human Decisions", "978-0-06-234739-8"));
        books.add(new Book(false, "Gene Kim, Kevin Behr, and George Spafford", "The Phoenix Project: A Novel About IT, DevOps, and Helping Your Business Win", "978-0-9885208384"));
        books.add(new Book(false, "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein", "Introduction to Algorithms", "978-0262033848"));
        books.add(new Book(false, "Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides", "Design Patterns: Elements of Reusable Object-Oriented Software", "978-0201633610"));
        books.add(new Book(false, "Charles Petzold", "Code: The Hidden Language of Computer Hardware and Software", "978-0735611313"));
        books.add(new Book(false, "Yuval Noah Harari", "Sapiens: A Brief History of Humankind", "978-0062316110"));

        for(Book book : books){
            this.bookDAO.persist(book);
        }
    }
}
