package org.sdutka.libraryapp.dao;

import org.sdutka.libraryapp.model.Book;
import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    void delete(int id);
    void update(Book book);
    void create(Book book);
    List<Book> getByPattern(String pattern);
    void persist(Book book);
}
