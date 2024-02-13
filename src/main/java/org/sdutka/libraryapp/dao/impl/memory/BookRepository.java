package org.sdutka.libraryapp.dao.impl.memory;

import org.sdutka.libraryapp.dao.IBookDAO;
import org.sdutka.libraryapp.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository implements IBookDAO {
    private final List<Book> books = new ArrayList<>();

    public BookRepository(){
//        this.books.add(new Book(1, "Clean Code", "Robert C. Martin", "978-0132350884", false));
//        this.books.add(new Book(2, "The Pragmatic Programmer", "Dave Thomas, Andy Hunt", "978-0201616224", true));
//        this.books.add(new Book(3, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", "978-0262033848", false));
//        this.books.add(new Book(4, "The Mythical Man-Month", "Frederick P. Brooks Jr.", "978-0201835953", false));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void delete(int id) {
        this.books.removeIf(book -> book.getId() == id);
    }

    @Override
    public void update(Book book) {
        //TODO jak bedzie baza danych
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(pattern.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(pattern.toLowerCase()))
                .toList();
    }

    @Override
    public void persist(Book book) {

    }
}
