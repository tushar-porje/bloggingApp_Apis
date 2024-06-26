package com.backend.blog.blog_app_apis.services.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.blog_app_apis.entities.Category;
import com.backend.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.backend.blog.blog_app_apis.payloads.CategoryDto;
import com.backend.blog.blog_app_apis.repositories.CategoryRepo;
import com.backend.blog.blog_app_apis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.mapper.map(categoryDto,Category.class);
        Category savedCategory=categoryRepo.save(category);
        return this.mapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        //updating category object according to given categorydto object to store in repo
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        //updating category object with categoryId and updated fields
        Category updatedUser=this.categoryRepo.save(category);
        return this.mapper.map(updatedUser, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        return this.mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=this.categoryRepo.findAll();
        return categories.stream().map(category->this.mapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        this.categoryRepo.delete(category);
    }
    
}
