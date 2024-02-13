package org.sdutka.libraryapp.dao;

import org.sdutka.libraryapp.model.Book;
import org.sdutka.libraryapp.model.BookReservation;
import org.sdutka.libraryapp.model.User;

import java.util.List;
import java.util.Optional;

public interface IRentDAO {
    void persist(BookReservation bookRent);
    Optional<BookReservation> getById(int id);
    List<BookReservation> getAllById(User user);
    List<BookReservation> getAll();
    List<BookReservation> getAllRented();
    List<BookReservation> getAllOverdue();
    void bookReturn(BookReservation bookRent);
}
