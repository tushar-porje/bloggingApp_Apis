package com.backend.blog.blog_app_apis.services;

import java.util.List;

import com.backend.blog.blog_app_apis.payloads.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categortyId);
    CategoryDto getCategoryById(Integer categortyId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Integer categortyId);
}
