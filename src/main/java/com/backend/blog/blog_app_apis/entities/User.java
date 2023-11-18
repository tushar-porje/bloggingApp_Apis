package com.backend.blog.blog_app_apis.entities;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    int id;
    @Column(name="user_name",nullable = false,length =100)
    String name;
    String email;
    String password;    
    String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Post> posts=new ArrayList<>();
}
