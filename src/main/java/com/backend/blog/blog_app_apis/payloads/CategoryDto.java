package com.backend.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class CategoryDto {

    Integer categoryId;

    @NotBlank(message = "Error: categoryTitle is blank!!")
    @Size(max = 100,message = "Error: categoryTitle is more than 100 character!!")
    String categoryTitle;
    @Size(min=10,message="Error: categoryDescription is less than 10")
    String categoryDescription;
}
