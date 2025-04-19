package com.gnana.blog.utility;

import lombok.Data;

@Data
public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;
    public static <T> ResponseStructure<T> createResponse(int status, String message, T data) {
        ResponseStructure<T> responseStructure = new ResponseStructure<T>();
        responseStructure.setStatus(status);
        responseStructure.setMessage(message);
        responseStructure.setData(data);
        return responseStructure;
    }
}
