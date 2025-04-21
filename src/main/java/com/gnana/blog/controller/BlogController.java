package com.gnana.blog.controller;

import com.gnana.blog.entity.Blog;
import com.gnana.blog.service.BlogService;
import com.gnana.blog.utility.PageResponseWrapper;
import com.gnana.blog.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class BlogController {
    private final BlogService blogService;

//    @GetMapping("/blogs")
//    public ResponseEntity<ResponseStructure<List<Blog>>> getAllBlogs(){
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .body(ResponseStructure.createResponse(HttpStatus.FOUND.value(),"Blogs found successfully",blogService.getAllBlogs()));
//    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> getBlogById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(),"Product found for id: "+id,blogService.getBlogById(id)));
    }
    @PostMapping("/blogs/create")
    public ResponseEntity<ResponseStructure<Blog>> createBlog(@RequestBody Blog blog){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseStructure.createResponse(HttpStatus.CREATED.value(), "Blog created with id: "+blog.getId(),blogService.createBlog(blog)));
    }
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> deleteBlog(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(), "Blog deleted for given id: "+id,blogService.deleteBlog(id)));
    }
    @PutMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> updateBlog(@RequestBody Blog blog,@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(),"Blog updated for given id: "+id,blogService.updateBlog(blog,id)));
    }
    @GetMapping("/blogs")
    public ResponseEntity<ResponseStructure<PageResponseWrapper<List<Blog>>>> findBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Blog> pages = blogService.findBlogs(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(),"Blogs found successfully",PageResponseWrapper.pageResponseWrapper(page,size,pages.getTotalPages(),pages.toList())));
    }
    @GetMapping("/users/blogs")
    public ResponseEntity<ResponseStructure<PageResponseWrapper<List<Blog>>>> findUserBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Blog> pages = blogService.findUserBlogs(page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(),"Blogs found successfully",PageResponseWrapper.pageResponseWrapper(page,size,pages.getTotalPages(),pages.toList())));
    }
}
