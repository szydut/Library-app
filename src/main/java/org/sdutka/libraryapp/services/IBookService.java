package org.sdutka.libraryapp.services;

import org.sdutka.libraryapp.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> getById(int id);
    List<Book> getAll();
    List<Book> getByPattern(String pattern);
    void persist(Book book);
    void update(Book book);
    void setUp();
}
