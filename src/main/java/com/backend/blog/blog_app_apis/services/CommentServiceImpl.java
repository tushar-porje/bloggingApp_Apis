package com.backend.blog.blog_app_apis.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.blog_app_apis.entities.Comment;
import com.backend.blog.blog_app_apis.entities.Post;
import com.backend.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.backend.blog.blog_app_apis.payloads.CommentDto;
import com.backend.blog.blog_app_apis.repositories.CommentRepo;
import com.backend.blog.blog_app_apis.repositories.PostRepo;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment =  this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedcomment  = this.commentRepo.save(comment);
		return this.modelMapper.map(savedcomment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		
		this.commentRepo.delete(comment);
		
		
	}

}
