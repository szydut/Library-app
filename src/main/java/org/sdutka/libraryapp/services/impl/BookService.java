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
        books.add(new Book(1, false, "Clean Code", "Robert C. Martin", "978-0132350884"));
        books.add(new Book(2, false, "The Pragmatic Programmer", "Dave Thomas, Andy Hunt", "978-0201616224"));
        books.add(new Book(3,true, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", "978-0262033848"));
        books.add(new Book(4, false, "The Mythical Man-Month", "Frederick P. Brooks Jr.", "978-0201835953"));
    }
}
