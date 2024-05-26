package com.backend.blog.blog_app_apis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.backend.blog.blog_app_apis.security.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    
    
    @Bean
    UserDetailsService userDetailsService(){
        return new MyUserDetailService();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/api/posts/").hasRole("ADMIN")
                                .requestMatchers("api/users/").hasRole("USER")
                                .requestMatchers("api/categories/").hasRole("USER")
                                .anyRequest().authenticated()
                ).httpBasic();

        return httpSecurity.build();
    }



}
