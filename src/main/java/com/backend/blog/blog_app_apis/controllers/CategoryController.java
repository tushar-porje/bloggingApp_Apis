package com.backend.blog.blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.blog_app_apis.payloads.ApiResponse;
import com.backend.blog.blog_app_apis.payloads.CategoryDto;
import com.backend.blog.blog_app_apis.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    

    @PostMapping("/categories/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategoryDto,HttpStatus.CREATED);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/categories/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos=this.categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto updatedCategoryDto=this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategoryDto,HttpStatus.OK);
    }
 
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse("category of id: " +categoryId +" deleted successfully",true));
    }

}
