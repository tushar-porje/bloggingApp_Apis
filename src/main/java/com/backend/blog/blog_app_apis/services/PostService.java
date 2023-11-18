package com.backend.blog.blog_app_apis.services;

import java.util.List;
import com.backend.blog.blog_app_apis.payloads.PostDto;

public interface PostService {
    //create
    PostDto createPost(Integer userId,Integer categoryId,PostDto postDto);
    //getbyid
    PostDto getPostById(Integer postId);
    //getallPost
    List<PostDto> getAllPost();
    //update
    PostDto updatePost(PostDto postDto,Integer postId);
    //deletePost
    void deletePost(Integer postId);
    //getPostByUser
    List<PostDto> getPostByUser(Integer userId);
    //getPostByCategory
    List<PostDto> getPostByCategory(Integer categoryId);
}
