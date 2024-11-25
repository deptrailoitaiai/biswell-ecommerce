package org.example.exceptions.CategoryExceptions;

public class CategoryExistedException extends RuntimeException {
    public CategoryExistedException(String message){
        super(message);
    }
}
