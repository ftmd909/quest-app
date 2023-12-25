package com.project.fd.services;
import java.util.List;
import java.util.Optional;

import com.project.fd.request.PostCreateRequest;
import com.project.fd.request.PostUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.fd.entities.Post;
import com.project.fd.entities.User;
import com.project.fd.repos.PostRepository;

@Service
public class PostService {
	private PostRepository postRepository;
	private UserService userService;

	@Autowired
	public PostService(PostRepository postRepository,UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	//get All posts
	public List<Post> getAllPosts(Optional<Long> userId) {
		if(userId.isPresent()) {
			return postRepository.findByUserId(userId.get());
		}
		return postRepository.findAll();
	}

	// get one post
	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

/*	public Post createOnePostById(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if(user == null){
			return null;}
		else{
			Post toSave = new Post();
			toSave.setId(newPostRequest.getId());
			toSave.setText(newPostRequest.getText());
			toSave.setTitle(newPostRequest.getTitle());
			toSave.setUser(user);
			return postRepository.save(toSave);
		}

	}*/
@Transactional
	public Post createOnePostById(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if (user == null) {
			throw new EntityNotFoundException("User not found with ID: " + newPostRequest.getUserId());
		} else {
			Post toSave = new Post();
			toSave.setId(newPostRequest.getId());
			toSave.setText(newPostRequest.getText());
			toSave.setTitle(newPostRequest.getTitle());
			toSave.setUser(user);
			return postRepository.save(toSave);
		}
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
		
	}



}
