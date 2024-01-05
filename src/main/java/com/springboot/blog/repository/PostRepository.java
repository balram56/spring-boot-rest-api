package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    //Build Get Posts by Category REST API
    List<Post> findByCategoryId(long categoryId);
}
