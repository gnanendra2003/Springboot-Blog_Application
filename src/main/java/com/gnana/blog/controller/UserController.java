package com.gnana.blog.controller;

import com.gnana.blog.entity.User;
import com.gnana.blog.service.UserService;
import com.gnana.blog.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.createResponse(HttpStatus.CREATED.value(),
                "User created with email: "+user.getEmail(), userService.registerUser(user)));
    }
    @GetMapping("/users")
    public ResponseEntity<ResponseStructure<User>> findByEmail(){
        return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.createResponse(HttpStatus.OK.value(),
                "User found with email: "+ SecurityContextHolder.getContext().getAuthentication().getName(), userService.findByEmail()));
    }
}
