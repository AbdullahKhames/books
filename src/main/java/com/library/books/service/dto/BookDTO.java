package com.library.books.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDTO {
    private String title;
    private String authors;
    private String publisher;
    private String isbn;
    private int year;
    private int price;
}
