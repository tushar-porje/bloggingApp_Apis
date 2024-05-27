package com.backend.blog.blog_app_apis.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthResponse {
    private String token;
    private String username;
}
