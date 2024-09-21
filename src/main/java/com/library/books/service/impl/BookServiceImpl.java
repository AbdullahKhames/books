package com.library.books.service.impl;

import com.library.books.controller.exception.BookISBNAlreadyExistException;
import com.library.books.controller.exception.BookISBNMismatchException;
import com.library.books.controller.exception.BookISBNNotFoundException;
import com.library.books.repository.BookRepository;
import com.library.books.repository.entity.Book;
import com.library.books.service.IBookService;
import com.library.books.service.dto.BookDTO;
import com.library.books.service.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        if (bookRepository.existsByIsbn(bookDTO.getIsbn())){
            throw new BookISBNAlreadyExistException("given isbn already exist : " + bookDTO.getIsbn());
        }
        Book book = bookMapper.dtoToEntity(bookDTO);
        return bookMapper.entityToDTO(bookRepository.save(book));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::entityToDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getAllBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthorsContainingIgnoreCase(author)
                .stream()
                .map(bookMapper::entityToDTO)
                .toList();    }

    @Override
    public BookDTO getBookByISBN(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .map(bookMapper::entityToDTO)
                .orElseThrow(() -> new BookISBNNotFoundException("given isbn does not exist : " + isbn));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBookByISBN(String isbn) {
        if (!bookRepository.existsByIsbn(isbn)){
            throw new BookISBNNotFoundException("given isbn not found : " + isbn);
        }
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateBookByISBN(String isbn, BookDTO bookDTO) {
        if (!isbn.equals(bookDTO.getIsbn())){
            throw new BookISBNMismatchException("given isbn does not match the entityBody ISBN : " + isbn);
        }
        return bookRepository.updateBookByIsbn(isbn,
                        bookDTO.getAuthors(),
                        bookDTO.getTitle(),
                        bookDTO.getPublisher(),
                        bookDTO.getYear(),
                        bookDTO.getPrice());
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
