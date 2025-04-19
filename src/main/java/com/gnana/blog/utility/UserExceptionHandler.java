package com.gnana.blog.utility;

import com.gnana.blog.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {


    public ResponseEntity<ErrorStructure> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorStructure.createErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found by given id", ex.getMessage()));
    }
    //@ExceptionHandler
    //	public ResponseEntity<ErrorStructure> handleProductNotFoundByIdException(ProductNotFoundByIdException ex) {
    //		return ResponseEntity
    //				.status(HttpStatus.NOT_FOUND)
    //				.body(ErrorStructure.createErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Product not found by given ID"));
    //	}
}
