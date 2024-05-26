package com.backend.blog.blog_app_apis.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(length = 100,nullable = false)
    private String postTitle;
    @Column(length = 10000)
    private String content;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<>();

}
