package com.library.books.controller.exception;

public class BookISBNNotFoundException extends RuntimeException{
    public BookISBNNotFoundException(String message) {
        super(message);
    }
}
