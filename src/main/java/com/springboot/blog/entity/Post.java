package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "content",nullable = false)
    private String content;

    //one post have many comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    //many post have one category
    //fetch type Eagar means -when ever  load post jpa entity then the category object is loded immedetly
    //we that demand on category object by getCategory method
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")//category_id -> is foreign key in posts tables
    private Category category;
}
