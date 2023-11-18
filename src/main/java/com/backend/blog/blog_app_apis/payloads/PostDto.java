package com.backend.blog.blog_app_apis.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
public class PostDto {

    private Integer postId;
    @NotBlank(message = "Error: postTitle is blank")
    private String postTitle;
    @NotBlank(message = "Error: content is blank")
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;

}
