package com.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponcedto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String username;
    private long likeCount;
}
