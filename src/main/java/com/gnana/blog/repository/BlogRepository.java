package com.gnana.blog.repository;

import com.gnana.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByOrderByCreatedAtDesc();
}
