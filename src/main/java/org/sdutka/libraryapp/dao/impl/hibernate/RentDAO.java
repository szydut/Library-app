package org.sdutka.libraryapp.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.sdutka.libraryapp.dao.IRentDAO;
import org.sdutka.libraryapp.model.BookReservation;
import org.sdutka.libraryapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentDAO implements IRentDAO {
    private final String GET_BY_ID = "FROM org.sdutka.libraryapp.model.BookReservation WHERE id = :id";
    private final String GET_ALL_RENTED_BOOKS_BY_USER_ID ="FROM org.sdutka.libraryapp.model.BookReservation WHERE user.id = :user_id";
    private final String GET_ALL ="FROM org.sdutka.libraryapp.model.BookReservation";
    private final String GET_ALL_RENTED_BOOKS ="FROM org.sdutka.libraryapp.model.BookReservation WHERE returnBookDate IS NULL";
    private final String GET_ALL_OVERDUE_BOOKS =
            "FROM org.sdutka.libraryapp.model.BookReservation " +
                    "WHERE (returnBookDate IS NULL AND DATEDIFF(endRentDate, current_date ) <= -1)" +
                    "OR (returnBookDate IS NOT NULL AND DATEDIFF(endRentDate, returnBookDate ) <= -1)";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persist(BookReservation bookRent) {
        Session session = this.sessionFactory.openSession();
        bookRent.setBook(session.merge(bookRent.getBook()));
        try {
            session.beginTransaction();
            session.persist(bookRent);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<BookReservation> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<BookReservation> query = session.createQuery(GET_BY_ID,BookReservation.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }finally {
            session.close();
        }
    }

    @Override
    public List<BookReservation> getAllById(User user) {
        Session session = this.sessionFactory.openSession();
        Query<BookReservation> query = session.createQuery(GET_ALL_RENTED_BOOKS_BY_USER_ID, BookReservation.class);
        query.setParameter("user_id", user.getId());
        List<BookReservation> queryResult = query.getResultList();
        session.close();
        return queryResult;
    }

    @Override
    public List<BookReservation> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<BookReservation> query = session.createQuery(GET_ALL, BookReservation.class);
        List<BookReservation> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<BookReservation> getAllRented() {
        Session session = this.sessionFactory.openSession();
        Query<BookReservation> query = session.createQuery(GET_ALL_RENTED_BOOKS,BookReservation.class);
        List<BookReservation> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<BookReservation> getAllOverdue() {
        Session session = this.sessionFactory.openSession();
        Query<BookReservation> query = session.createQuery(GET_ALL_OVERDUE_BOOKS,BookReservation.class);
        List<BookReservation> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void bookReturn(BookReservation bookRent) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(bookRent);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
