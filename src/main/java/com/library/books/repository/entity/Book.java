package com.library.books.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "book_title")
    private String title;
    private String authors;
    private String publisher;
    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(name = "year_published")
    private int year;
    private int price;
}
