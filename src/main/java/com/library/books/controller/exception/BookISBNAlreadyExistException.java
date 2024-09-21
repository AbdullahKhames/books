package com.library.books.controller.exception;

public class BookISBNAlreadyExistException extends RuntimeException {
    public BookISBNAlreadyExistException(String message) {
        super(message);
    }
}
