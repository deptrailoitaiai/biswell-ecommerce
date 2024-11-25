package org.example.exceptions.ItemExceptions;

public class ItemExistedException extends RuntimeException {
    public ItemExistedException(String message) {
        super(message);
    }
}
