package com.library.books.controller;

import com.library.books.service.IBookService;
import com.library.books.service.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final IBookService bookService;

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsForBooksURI(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.DELETE)
                .build();
    }

    @RequestMapping(path = "{isbn}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsForISBNURI(){
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.PUT, HttpMethod.HEAD, HttpMethod.DELETE)
                .build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO, UriComponentsBuilder uriBuilder){
        BookDTO processedBookDto = bookService.addBook(bookDTO);
        URI uri = uriBuilder.path("/books/" + processedBookDto.getIsbn()).buildAndExpand(processedBookDto.getIsbn()).toUri();
        return ResponseEntity.created(uri).body(processedBookDto);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/book")
    public ResponseEntity<List<BookDTO>> getAllBooksByAuthor(@RequestParam String author){
        return ResponseEntity.ok(bookService.getAllBooksByAuthor(author));
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteAllBooks(){
        bookService.deleteAllBooks();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Integer> updateBook(@PathVariable String isbn, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok().body(bookService.updateBookByISBN(isbn, bookDTO));
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> getBook(@PathVariable String isbn){
        return ResponseEntity.ok().body(bookService.getBookByISBN(isbn));
    }
    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn){
        bookService.deleteBookByISBN(isbn);
        return ResponseEntity.noContent().build();
    }

}
