package com.library.books.service.mapper;

import com.library.books.repository.entity.Book;
import com.library.books.service.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book dtoToEntity(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthors(bookDTO.getAuthors());
        book.setPublisher(bookDTO.getPublisher());
        book.setIsbn(bookDTO.getIsbn());
        book.setYear(bookDTO.getYear());
        book.setPrice(bookDTO.getPrice());
        return book;
    }


    public BookDTO entityToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthors(book.getAuthors());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setYear(book.getYear());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }
}
