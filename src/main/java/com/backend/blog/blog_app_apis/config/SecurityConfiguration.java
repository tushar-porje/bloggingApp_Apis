package com.backend.blog.blog_app_apis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new MyUserDetailService();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((a) -> a.disable())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                        .requestMatchers("/api/posts")
                        .hasAuthority("ADMIN")
                        .requestMatchers("/api/users")
                        .hasAuthority("USER")
                        .anyRequest()
                        .authenticated() // authenticate all other requests
                ).formLogin();

        return httpSecurity.build();
    }



}
