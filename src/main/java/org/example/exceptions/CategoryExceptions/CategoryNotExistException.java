package org.example.exceptions.CategoryExceptions;

public class CategoryNotExistException extends RuntimeException {
    public CategoryNotExistException(String message) {
        super(message);
    }
}
