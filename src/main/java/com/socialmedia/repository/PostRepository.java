package com.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userID);
}
