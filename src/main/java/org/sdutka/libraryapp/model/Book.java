package org.sdutka.libraryapp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "tbook")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean rentStatus;
    private String title;
    private String author;
    private String isbn;

    public Book(int id) {
        this.id = id;
    }

    public Book(boolean rentStatus, String title, String author, String isbn) {
        this.rentStatus = rentStatus;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
