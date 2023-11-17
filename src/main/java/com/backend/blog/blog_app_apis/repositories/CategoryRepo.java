package com.backend.blog.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.blog_app_apis.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
    
}
