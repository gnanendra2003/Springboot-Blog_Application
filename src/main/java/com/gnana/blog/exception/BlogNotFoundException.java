package com.gnana.blog.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
