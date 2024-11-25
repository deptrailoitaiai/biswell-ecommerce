package org.example.exceptions.ArticleExceptions;

public class ArticleExistedException extends RuntimeException {
    public ArticleExistedException(String message) {
        super(message);
    }
}
