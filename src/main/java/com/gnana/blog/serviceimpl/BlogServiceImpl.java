package com.gnana.blog.serviceimpl;

import com.gnana.blog.entity.Blog;
import com.gnana.blog.entity.User;
import com.gnana.blog.exception.BlogNotFoundException;
import com.gnana.blog.exception.UserNotFoundException;
import com.gnana.blog.repository.BlogRepository;
import com.gnana.blog.repository.UserRepository;
import com.gnana.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog not found by id: "+id));
    }

    @Override
    public Blog createBlog(Blog blog, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog blog){
        return blogRepository.findById(blog.getId()).map( oldBlog -> {
            oldBlog.setTitle(blog.getTitle());
            oldBlog.setContent(blog.getContent());
            return oldBlog;
        }).orElseThrow(() -> new BlogNotFoundException("Blog not found by id: "+blog.getId()));
    }
    @Override
    public Blog deleteBlog(int id) {
        return blogRepository.findById(id).map(blog -> {
            blogRepository.delete(blog);
            return blog;
        }).orElseThrow(()->new BlogNotFoundException("Blog not found by id: "+id));

    }

}
