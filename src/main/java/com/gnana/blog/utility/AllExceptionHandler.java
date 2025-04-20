package com.gnana.blog.utility;

import com.gnana.blog.exception.BlogNotFoundException;
import com.gnana.blog.exception.UserAlreadyExistsException;
import com.gnana.blog.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorStructure.createErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found by given email", ex.getMessage()));
    }
    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleBlogNotFoundException(BlogNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorStructure.createErrorResponse(HttpStatus.NOT_FOUND.value(), "Blog Not Found by given id", ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorStructure.createErrorResponse(HttpStatus.CONFLICT.value(), "User already exists", ex.getMessage()));
    }
    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleAllOtherException(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorStructure.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Error", ex.getMessage()));
    }
}
