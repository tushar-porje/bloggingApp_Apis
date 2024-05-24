package com.backend.blog.blog_app_apis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.blog.blog_app_apis.entities.User;
import com.backend.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.backend.blog.blog_app_apis.repositories.UserRepo;

public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user=userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email", username));
        
        return new UserDetail(user.getEmail(),passwordEncoder.encode(user.getPassword()),user.getRoles());
    }
    
}
