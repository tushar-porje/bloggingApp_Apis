package com.backend.blog.blog_app_apis.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.blog_app_apis.entities.Role;
import java.util.Optional;


public interface RoleRepo extends JpaRepository<Role,Integer>{
    Optional<Role> findByName(String name);
}
