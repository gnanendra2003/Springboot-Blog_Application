package com.gnana.blog.serviceimpl;

import com.gnana.blog.entity.User;
import com.gnana.blog.exception.UserNotFoundException;
import com.gnana.blog.repository.UserRepository;
import com.gnana.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findById(email).orElseThrow(()-> new UserNotFoundException("User not found with email: " + email));
    }
}
