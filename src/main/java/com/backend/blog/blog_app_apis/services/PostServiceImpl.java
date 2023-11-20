package com.backend.blog.blog_app_apis.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.backend.blog.blog_app_apis.entities.Category;
import com.backend.blog.blog_app_apis.entities.Post;
import com.backend.blog.blog_app_apis.entities.User;
import com.backend.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.backend.blog.blog_app_apis.payloads.PostDto;
import com.backend.blog.blog_app_apis.payloads.PostResponse;
import com.backend.blog.blog_app_apis.repositories.CategoryRepo;
import com.backend.blog.blog_app_apis.repositories.PostRepo;
import com.backend.blog.blog_app_apis.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PostRepo postRepo;

    @Override
    public PostDto createPost(Integer userId, Integer categoryId, PostDto postDto) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        Post post=this.mapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setCategory(category);
        post.setUser(user);
        post.setAddedDate(new Date());
        Post savedPost=this.postRepo.save(post);
        System.out.println(savedPost);
        return this.mapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        return this.mapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String order) {
        Direction direction=order.equalsIgnoreCase("asc")?Direction.ASC:Direction.DESC;
        
        Pageable p=PageRequest.of(pageNumber, pageSize, direction,sortBy);
        Page<Post> postPage=this.postRepo.findAll(p);
        List<Post> allPostsInPage=postPage.getContent();
        List<PostDto> allPostDtos=allPostsInPage.stream().map(post->this.mapper.map(post,PostDto.class)).toList();
        PostResponse postResponse=new PostResponse();
        postResponse.setPostDtos(allPostDtos);
        postResponse.setLastPage(postPage.isLast());
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setTotalPosts(postPage.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setImageName(postDto.getImageName());
        post.setContent(postDto.getContent());
        Post updatedpost=this.postRepo.save(post);
        return this.mapper.map(updatedpost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map(post->this.mapper.map(post, PostDto.class)).toList();
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map(post->this.mapper.map(post, PostDto.class)).toList();
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts=this.postRepo.findByPostTitleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map(post->this.mapper.map(post, PostDto.class)).toList();
        return postDtos;
    }
    
}
