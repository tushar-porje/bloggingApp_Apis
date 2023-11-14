package com.backend.blog.blog_app_apis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    
    @GetMapping("/greeting")
    public String demoo(){
        return "tushar";
    }
}
