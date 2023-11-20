package com.backend.blog.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.blog_app_apis.entities.Category;
import com.backend.blog.blog_app_apis.entities.Post;
import com.backend.blog.blog_app_apis.entities.User;

import java.util.List;


public interface PostRepo extends JpaRepository<Post,Integer>{
    
    List<Post> findByCategory(Category Category);
    List<Post> findByUser(User user);
    List<Post> findByPostTitleContaining(String postTitle);
}
