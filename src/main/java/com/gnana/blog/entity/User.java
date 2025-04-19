package com.gnana.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String email;
    @Column(nullable = false)
    private String password;
    private String name;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Blog> blogs;

}
