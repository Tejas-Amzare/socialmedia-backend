package com.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponsedto {
    private Long id;
    private String username;
    private String email;
}
