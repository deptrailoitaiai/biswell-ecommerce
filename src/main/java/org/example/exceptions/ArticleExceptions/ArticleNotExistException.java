package org.example.exceptions.ArticleExceptions;

public class ArticleNotExistException extends RuntimeException {
    public ArticleNotExistException(String message) {
        super(message);
    }
}
