package org.example.exceptions.AlbumExceptions;

public class AlbumExistedException extends RuntimeException {
    public AlbumExistedException(String message) {
        super(message);
    }
}
