package com.socialmedia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmedia.model.Comment;
import com.socialmedia.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    void deleteByPost(Post post);

}
