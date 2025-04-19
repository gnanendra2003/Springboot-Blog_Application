package com.gnana.blog.security;

import com.gnana.blog.entity.User;
import com.gnana.blog.exception.UserNotFoundException;
import com.gnana.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException{
        User user = userRepository.findById(username).orElseThrow(()-> new UserNotFoundException("User Not Found By email: "+username));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

    }
}
