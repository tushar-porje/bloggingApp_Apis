package com.backend.blog.blog_app_apis.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class PostResponse {
    private List<PostDto> postDtos;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalPosts;
    private Integer totalPages;
    private boolean isLastPage;
    
}
