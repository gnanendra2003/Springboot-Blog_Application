package com.gnana.blog.service;

import com.gnana.blog.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

//    List<Blog> getAllBlogs();
    Blog getBlogById(long id);
    Blog createBlog(Blog blog);
    Blog deleteBlog(long id);
    Blog updateBlog(Blog blog,long id);
    Page<Blog> findBlogs(int page, int size);
    Page<Blog> findUserBlogs(int page, int size);
}
