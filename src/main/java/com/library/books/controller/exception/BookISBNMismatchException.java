package com.library.books.controller.exception;

public class BookISBNMismatchException extends RuntimeException{
    public BookISBNMismatchException(String message) {
        super(message);
    }
}
