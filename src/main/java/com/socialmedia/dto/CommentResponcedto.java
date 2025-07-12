package com.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponcedto {
    private Long id;
    private String content;
    private String username;
    private String postTitle;
}
