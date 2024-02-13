package org.sdutka.libraryapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "tbookreservation")
public class BookReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;

    @ManyToOne
    private User user;

    private LocalDateTime startRentDate;
    private LocalDateTime endRentDate;
    private LocalDateTime returnBookDate;
}
