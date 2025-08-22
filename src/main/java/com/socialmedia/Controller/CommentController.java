package com.socialmedia.Controller;

import com.socialmedia.dto.CommentResponcedto;
import com.socialmedia.dto.CreateCommentRequest;
import com.socialmedia.model.Comment;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import com.socialmedia.repository.CommentRepository;
import com.socialmedia.repository.PostRepository;
import com.socialmedia.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Operation( summary = "  - Careate Comment -  ")
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody CreateCommentRequest request) {
        Optional<Post> postOpt = postRepository.findById(request.getPostId());
        Optional<User> userOpt = userRepository.findById(request.getUserId());

        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post not found");
        }

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(postOpt.get())
                .user(userOpt.get())
                .build();

        Comment saved = commentRepository.save(comment);

        CommentResponcedto dto = new CommentResponcedto(
                saved.getId(),
                saved.getContent(),
                saved.getUser().getUsername(),
                saved.getPost().getTitle());

        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "  - Get Comment Using Post Id -")
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponcedto>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentResponcedto> dtos = comments.stream()
                .map(c -> new CommentResponcedto(
                        c.getId(),
                        c.getContent(),
                        c.getUser().getUsername(),
                        c.getPost().getTitle()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // DELETE comment by ID
    @Operation(summary = "  - Delete comment Using Comment ID -")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }
    }

    // UPDATE comment by ID
    @Operation(summary = "  - Update Comment Using Comment Id -")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CreateCommentRequest request) {

        Optional<Comment> commentOpt = commentRepository.findById(id);
        Optional<Post> postOpt = postRepository.findById(request.getPostId());

        if (commentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
        }

        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post not found");
        }

        Comment comment = commentOpt.get();
        comment.setContent(request.getContent());
        comment.setPost(postOpt.get());

        commentRepository.save(comment);
        return ResponseEntity.ok("Comment updated successfully");
    }

}
