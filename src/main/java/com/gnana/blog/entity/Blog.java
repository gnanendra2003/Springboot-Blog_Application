package com.gnana.blog.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT" ,nullable = false)
    private String content;

    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updatedAt;

    @ManyToOne
    @JsonIgnore
    private User user;

}
