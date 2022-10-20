package com.example.demo.service;

import com.example.demo.domain.Post;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts() throws JsonProcessingException;
    Post getPostById(Long postId);
    List<Post> getAllPostsByUserId(Long userId);
    Post createPost(Post post);
    void updatePost(Long postId, Post post);
    void deletePost(Long postId);
}
