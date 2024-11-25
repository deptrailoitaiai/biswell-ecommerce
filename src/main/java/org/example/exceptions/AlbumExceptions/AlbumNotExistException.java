package org.example.exceptions.AlbumExceptions;

public class AlbumNotExistException extends RuntimeException {
    public AlbumNotExistException(String message) {
        super(message);
    }
}
