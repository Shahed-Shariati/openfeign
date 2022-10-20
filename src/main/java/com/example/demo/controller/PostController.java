package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private  PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(HttpSession session) throws JsonProcessingException {
        System.out.println(session.getAttribute("shaed"));
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId, HttpSession session) {
        session.setAttribute("shaed", "ddd");
        return postService.getPostById(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(Post post) { return postService.createPost(post); }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId, Post post) { postService.updatePost(postId, post); }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) { postService.deletePost(postId); }
}
