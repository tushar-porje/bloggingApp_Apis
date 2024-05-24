package com.backend.blog.blog_app_apis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    
    // @Bean
    // UserDetailsService userDetailsService(){
    //     return new MyUserDetailsService();
    // }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((a)->a.disable())
        .authorizeHttpRequests(authorizeHttpRequests ->
        authorizeHttpRequests
            // .requestMatchers("/incidentmanagementsystem/apis/v1/registerUser").permitAll() // make this endpoint public
            .anyRequest().authenticated() // authenticate all other requests
        ).httpBasic();

        return httpSecurity.build();
    }

}
