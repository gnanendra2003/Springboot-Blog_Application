package com.gnana.blog.serviceimpl;

import com.gnana.blog.entity.User;
import com.gnana.blog.exception.UserAlreadyExistsException;
import com.gnana.blog.exception.UserNotFoundException;
import com.gnana.blog.repository.UserRepository;
import com.gnana.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        if(userRepository.existsById(user.getEmail()))
            throw new UserAlreadyExistsException("User already exists by given email: "+user.getEmail());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }
    @Override
    public User findByEmail() {
        return userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new UserNotFoundException("User not found with email "));
    }
}
