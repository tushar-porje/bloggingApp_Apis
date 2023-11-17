package com.backend.blog.blog_app_apis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter@Setter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int CategoryId;
    @Column(name="title",length = 100,nullable = false)
    String categoryTitle;
    @Column(name = "description")
    String categoryDescription;

}
