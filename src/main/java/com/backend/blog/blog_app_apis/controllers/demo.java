package com.backend.blog.blog_app_apis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class demo {
    
    @GetMapping("/creater")
    public String creater(){
        return "tushar";
    }
}
