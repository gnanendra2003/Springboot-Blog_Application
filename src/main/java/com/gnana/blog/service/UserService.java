package com.gnana.blog.service;

import com.gnana.blog.entity.User;

public interface UserService {
    User registerUser(User user);
    User findByEmail();
}
