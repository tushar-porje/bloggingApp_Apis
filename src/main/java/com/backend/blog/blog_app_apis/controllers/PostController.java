package com.backend.blog.blog_app_apis.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.blog.blog_app_apis.payloads.ApiResponse;
import com.backend.blog.blog_app_apis.payloads.PostDto;
import com.backend.blog.blog_app_apis.payloads.PostResponse;
import com.backend.blog.blog_app_apis.services.FileService;
import com.backend.blog.blog_app_apis.services.PostService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@PathVariable Integer userId,@PathVariable Integer categoryId,
        @RequestBody PostDto postDto){
            PostDto createdPostDto=this.postService.createPost(userId, categoryId, postDto);          
            return new ResponseEntity<>(createdPostDto,HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtos=this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
    @RequestParam(name = "pageNumber",defaultValue = "0")Integer pageNumber,
    @RequestParam(name = "pageSize",defaultValue =  "3")Integer pageSize,
    @RequestParam(name = "sortBy",defaultValue = "postTitle")String sortBy,
    @RequestParam(name = "order",defaultValue = "asc")String order){

        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,order);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPostDto=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post of id: " +postId +" deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords){
        List<PostDto> postDtos=this.postService.searchPost(keywords);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
        @PathVariable Integer postId) throws IOException{
        
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        
        postDto.setImageName(fileName);
        PostDto updatedPost =  this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
        
    }
    
    //method to serve files
    @GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
        
    }


}