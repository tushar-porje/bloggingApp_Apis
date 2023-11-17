package com.backend.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class UserDto {
    int id;
    @NotBlank(message = "Error: name is blank!!")
    @Size(min = 4,max = 100,message = "Error: name is less than 4 or more than 100 character!!")
    String name;
    @Email(message = "Error: Email is not in proper format!!")
    String email;
    @NotBlank(message = "Error: password is blank!!")
    @Size(min = 4,max = 12,message = "Error: password is less than 4 or greater than 12!!")
    String password;
    @NotBlank(message = "Error: about section is blank!!")
    String about;
}
