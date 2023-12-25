package com.project.fd.controllers;

import com.project.fd.entities.Post;
import com.project.fd.request.PostCreateRequest;
import com.project.fd.request.PostUpdateRequest;
import com.project.fd.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        super();
        this.postService = postService;
    }


    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }


    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
        return postService.createOnePostById(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePostById(postId);
    }


    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
        return postService.updateOnePostById(postId,updatePost);

    }

    @DeleteMapping("/{postId}")
    public void deleteOnePostById(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
