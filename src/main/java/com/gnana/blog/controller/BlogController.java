package com.gnana.blog.controller;

import com.gnana.blog.entity.Blog;
import com.gnana.blog.service.BlogService;
import com.gnana.blog.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;

//    @GetMapping("/blogs")
//    public ResponseEntity<ResponseStructure<List<Blog>>> getAllBlogs(){
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .body(ResponseStructure.createResponse(HttpStatus.FOUND.value(),"Blogs found successfully",blogService.getAllBlogs()));
//    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> getBlogById(@PathVariable int id){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ResponseStructure.createResponse(HttpStatus.FOUND.value(),"Product found for id: "+id,blogService.getBlogById(id)));
    }
    @PostMapping("/blogs")
    public ResponseEntity<ResponseStructure<Blog>> createBlog(@RequestBody Blog blog, String email){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseStructure.createResponse(HttpStatus.CREATED.value(), "Blog created with id: "+blog.getId(),blogService.createBlog(blog,email)));
    }
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<ResponseStructure<Blog>> deleteBlog(@PathVariable int id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(), "Blog deleted for given id: "+id,blogService.deleteBlog(id)));
    }
    @PutMapping("/blogs")
    public ResponseEntity<ResponseStructure<Blog>> updateBlog(Blog blog){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseStructure.createResponse(HttpStatus.OK.value(),"Blog updated for given id: "+blog.getId(),blogService.updateBlog(blog)));
    }
    @GetMapping("/blogs")
    public ResponseEntity<ResponseStructure<List<Blog>>> findBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(ResponseStructure.createResponse(HttpStatus.FOUND.value(),"Blogs found successfully",blogService.findBlogs(page, size)));
    }
}
