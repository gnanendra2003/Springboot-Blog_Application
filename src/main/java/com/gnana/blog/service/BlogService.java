package com.gnana.blog.service;

import com.gnana.blog.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();
    Blog getBlogById(int id);
    Blog createBlog(Blog blog, String email);
    Blog deleteBlog(int id);
}
