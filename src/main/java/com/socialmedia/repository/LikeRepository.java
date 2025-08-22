package com.socialmedia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.socialmedia.model.Like;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM Like l WHERE l.post = :post")
    void deleteByPost(Post post);

    List<Like> findByPost(Post post);

    long countByPost(Post post);
}
