package org.sdutka.libraryapp.services.impl;

import jakarta.annotation.Resource;
import org.sdutka.libraryapp.dao.IRentDAO;
import org.sdutka.libraryapp.model.Book;
import org.sdutka.libraryapp.model.BookReservation;
import org.sdutka.libraryapp.model.User;
import org.sdutka.libraryapp.services.IBookService;
import org.sdutka.libraryapp.services.IRentService;
import org.sdutka.libraryapp.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentService implements IRentService {

    @Autowired
    IRentDAO rentDAO;

    @Autowired
    IBookService bookService;

    @Resource
    SessionObject sessionObject;

    @Override
    public void persist(int bookId) {
        BookReservation rent = new BookReservation();
        Book book = this.bookService.getById(bookId).get();
        book.setRentStatus(true);
        rent.setBook(book);
        rent.setUser(this.sessionObject.getLoggedUser());
        rent.setStartRentDate(LocalDateTime.now());
        rent.setEndRentDate(LocalDateTime.now().plusWeeks(2));
        rentDAO.persist(rent);
    }

    @Override
    public List<BookReservation> getAllById(User user) {
        return this.rentDAO.getAllById(user);
    }

    @Override
    public List<BookReservation> getAll() {
        return this.rentDAO.getAll();
    }

    @Override
    public List<BookReservation> getAllRented() {
        return this.rentDAO.getAllRented();
    }

    @Override
    public List<BookReservation> getAllOverdue() {
        return this.rentDAO.getAllOverdue();
    }

    @Override
    public void bookReturn(int rentId) {
        BookReservation rent = this.rentDAO.getById(rentId).get();
        Book book = rent.getBook();
        rent.setReturnBookDate(LocalDateTime.now());
        book.setRentStatus(false);
        this.rentDAO.bookReturn(rent);
        this.bookService.update(book);
    }
}
