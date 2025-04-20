package com.gnana.blog.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
public class PageResponseWrapper <T>{
    private int page;
    private int pageSize;
    private int totalPages;
    private T data;

    public static <T> PageResponseWrapper<T> pageResponseWrapper(int page, int pageSize, int totalPages,T data){
        return new PageResponseWrapper<>(page, pageSize,totalPages,data);
    }
}
