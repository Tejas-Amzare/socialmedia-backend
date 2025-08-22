package com.socialmedia.Controller;

import com.socialmedia.dto.CreatePostRequest;
import com.socialmedia.dto.PostResponcedto;
import com.socialmedia.model.Like;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import com.socialmedia.repository.CommentRepository;
import com.socialmedia.repository.LikeRepository;
import com.socialmedia.repository.PostRepository;
import com.socialmedia.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private CommentRepository CommentRepository;

    // =========================== POST RELATED ENDPOINTS
    // ===========================

    @Operation(summary = "  - Create post Using Post ID -")
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .user(userOpt.get())
                .build();

        Post saved = postRepository.save(post);

        // include like count in response
        long likeCount = likeRepository.countByPost(saved);

        return ResponseEntity.ok(new PostResponcedto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor(),
                saved.getUser().getUsername(),
                likeCount));
    }

    @Operation(summary = "  - Get All Post  -")
    @GetMapping("/getAll")
    public ResponseEntity<List<PostResponcedto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        // include like count in each PostResponcedto
        List<PostResponcedto> dtos = posts.stream()
                .map(p -> new PostResponcedto(
                        p.getId(),
                        p.getTitle(),
                        p.getContent(),
                        p.getAuthor(),
                        p.getUser().getUsername(),
                        likeRepository.countByPost(p) // like count
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "  - Get Post Using User ID -")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponcedto>> getPostsByUserId(@PathVariable Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        List<PostResponcedto> response = posts.stream()
                .map(p -> new PostResponcedto(
                        p.getId(),
                        p.getTitle(),
                        p.getContent(),
                        p.getAuthor(),
                        p.getUser().getUsername(),
                        likeRepository.countByPost(p) // Added like count
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "  - Update Post Using Post ID -")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @Valid @RequestBody CreatePostRequest request) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }

        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        Post post = postOpt.get();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(request.getAuthor());
        post.setUser(userOpt.get());

        postRepository.save(post);
        return ResponseEntity.ok("Post updated successfully");
    }

    @Operation(summary = " Delete Post Using ID")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }

        Post post = postOpt.get();

        // First delete related comments
        CommentRepository.deleteByPost(post);
        likeRepository.deleteByPost(post);

        // Then delete post
        postRepository.delete(post);

        return ResponseEntity.ok("Post and its comments deleted");
    }

    // =========================== LIKE RELATED ENDPOINTS
    // ===========================

    @Operation(summary = "  - Post Like Using Post ID")
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (postOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post or User not found");
        }

        Post post = postOpt.get();
        User user = userOpt.get();

        // Check if user already liked
        if (likeRepository.findByPostAndUser(post, user).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already liked this post");
        }

        Like like = Like.builder().post(post).user(user).build();
        likeRepository.save(like);

        return ResponseEntity.ok("Post liked successfully");
    }

    @Operation(summary = "  - Post Like Count Using Post ID -")
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
        }
        long count = likeRepository.countByPost(postOpt.get());
        return ResponseEntity.ok(count);
    }

    @Operation(summary = "  - Get Who like The Post -")
    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<String>> getLikeUsernames(@PathVariable Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }

        List<String> usernames = likeRepository.findByPost(postOpt.get())
                .stream()
                .map(like -> like.getUser().getUsername())
                .collect(Collectors.toList());

        return ResponseEntity.ok(usernames);
    }

    @Operation(summary = "  Remove Like On Post Using Post Id")
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId, @RequestParam Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (postOpt.isEmpty() || userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post or User not found");
        }

        Optional<Like> likeOpt = likeRepository.findByPostAndUser(postOpt.get(), userOpt.get());

        if (likeOpt.isPresent()) {
            likeRepository.delete(likeOpt.get());
            return ResponseEntity.ok("Post unliked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like not found");
        }
    }

}
