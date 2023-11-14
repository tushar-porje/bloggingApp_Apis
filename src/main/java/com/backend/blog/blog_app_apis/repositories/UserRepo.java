package com.backend.blog.blog_app_apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.blog.blog_app_apis.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    
}
