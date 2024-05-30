package com.backend.blog.blog_app_apis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.backend.blog.blog_app_apis.security.JwtAuthenticationEntryPoint;
import com.backend.blog.blog_app_apis.security.JwtAuthenticationFilter;

@Configuration
// @EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity // added bcz used @PreAuthorize("hasRole('ADMIN')") on getAllUser controller
                      // method
public class SecurityConfiguration {

    // @Bean
    // UserDetailsService userDetailsService(){
    // return new MyUserDetailService();
    // }

    // @Bean
    // PasswordEncoder passwordEncoder(){
    // return new BCryptPasswordEncoder();
    // }

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        final String[] PUBLIC_URLS = {
                "/api/auth/**", "/v3/api-docs","/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "webjars/**"
        };

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests()
                // .requestMatchers("/api/posts/").hasRole("ADMIN")
                // .requestMatchers("api/users/").hasRole("USER")
                // .requestMatchers("api/categories/").hasRole("USER")
                .requestMatchers(PUBLIC_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
