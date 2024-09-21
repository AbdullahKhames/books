package com.library.books.service;

import com.library.books.service.dto.BookDTO;

import java.util.List;

public interface IBookService {

    BookDTO addBook(BookDTO book);
    List<BookDTO> getAllBooks();
    List<BookDTO> getAllBooksByAuthor(String author);
    BookDTO getBookByISBN(String isbn);
    void deleteBookByISBN(String isbn);
    int updateBookByISBN(String isbn, BookDTO bookDTO);
    void deleteAllBooks();
}
