package org.example.exceptions;

import org.example.dtos.responses.GlobalResponseDto;
import org.example.exceptions.AlbumExceptions.AlbumExistedException;
import org.example.exceptions.AlbumExceptions.AlbumNotExistException;
import org.example.exceptions.ArticleExceptions.ArticleExistedException;
import org.example.exceptions.ArticleExceptions.ArticleNotExistException;
import org.example.exceptions.CategoryExceptions.CategoryExistedException;
import org.example.exceptions.CategoryExceptions.CategoryNotExistException;
import org.example.exceptions.ImageOfAlbumExceptions.ImageOfAlbumExistedException;
import org.example.exceptions.ImageOfAlbumExceptions.ImageOfAlbumNotExistException;
import org.example.exceptions.ItemExceptions.ItemExistedException;
import org.example.exceptions.ItemExceptions.ItemNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GlobalResponseDto<String>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e){
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                "HTTP method not supported for this endpoint."
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    // Album
    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleAlbumNotExistException (AlbumNotExistException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleAlbumExistedException(AlbumExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //Article
    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleArticleNotExistException(ArticleNotExistException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleArticleExistedException(ArticleExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //Category
    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleCategoryNotExistException(CategoryNotExistException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleCategoryExistedException(CategoryExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //ImageOfAlbum
    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleImageOfAlbumNotExistException(ImageOfAlbumNotExistException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleImageOfAlbumExistedException(ImageOfAlbumExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //Item
    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleItemExistedException(ItemExistedException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler()
    public ResponseEntity<GlobalResponseDto<String>> handleItemNotExistException(ItemNotExistException e) {
        GlobalResponseDto<String> response = new GlobalResponseDto<>(
                false,
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
