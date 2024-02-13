package org.sdutka.libraryapp.services;

import org.sdutka.libraryapp.model.BookReservation;
import org.sdutka.libraryapp.model.User;

import java.util.List;

public interface IRentService {
    void persist(int bookId);
    List<BookReservation> getAllById(User user);
    List<BookReservation> getAll();
    List<BookReservation> getAllRented();
    List<BookReservation> getAllOverdue();
    void bookReturn(int rentId);
}
