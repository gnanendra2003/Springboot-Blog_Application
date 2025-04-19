package com.gnana.blog.utility;

import lombok.Data;

@Data
public class ErrorStructure {
    private int status;
    private String message;
    private String rootCause;
    public static ErrorStructure createErrorResponse(int status, String message, String rootCause) {
        ErrorStructure errorStructure = new ErrorStructure();
        errorStructure.setStatus(status);
        errorStructure.setMessage(message);
        errorStructure.setRootCause(rootCause);
        return errorStructure;
    }
}
