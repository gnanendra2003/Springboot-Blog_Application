package com.gnana.blog.serviceimpl;

import com.gnana.blog.entity.Blog;
import com.gnana.blog.entity.User;
import com.gnana.blog.exception.BlogNotFoundException;
import com.gnana.blog.exception.UserNotFoundException;
import com.gnana.blog.repository.BlogRepository;
import com.gnana.blog.repository.UserRepository;
import com.gnana.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

//    @Override
//    public List<Blog> getAllBlogs() {
//        return blogRepository.findAllByOrderByCreatedAtDesc();
//    }

    @Override
    public Blog getBlogById(long id) {
        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog not found by id: "+id));
    }

    @Override
    public Blog createBlog(Blog blog) {
        User user = userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException("User not found with email "));
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog, long id){
        return blogRepository.findById(id).map( oldBlog -> {
            oldBlog.setTitle(blog.getTitle());
            oldBlog.setContent(blog.getContent());
            oldBlog.setUpdatedAt(new Date());
            return oldBlog;
        }).orElseThrow(() -> new BlogNotFoundException("Blog not found by id: "+id));
    }

    @Override
    public Page<Blog> findBlogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Blog> pages = blogRepository.findAll(pageable);
        return pages;
    }

    @Override
    public Page<Blog> findUserBlogs(int page, int size) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(page, size);
        return blogRepository.findByUserEmail(email, pageable);
    }

    @Override
    public Blog deleteBlog(long id) {
        return blogRepository.findById(id).map(blog -> {
            blogRepository.delete(blog);
            return blog;
        }).orElseThrow(()->new BlogNotFoundException("Blog not found by id: "+id));

    }


}
