package com.backend.blog.blog_app_apis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class UserDto {
    int id;
    String name;
    String email;
    String password;
    String about;
}
