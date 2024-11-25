package org.example.exceptions.ItemExceptions;

public class ItemNotExistException extends RuntimeException {
    public ItemNotExistException(String message) {
        super(message);
    }
}
