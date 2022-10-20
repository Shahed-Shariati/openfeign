package com.example.demo.feing;

import com.example.demo.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "postFeignClient",url = "${client.post.baseUrl}")
public interface PostFeignClient {

    @GetMapping("/posts")
    ResponseEntity<String> getAllPosts();

    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable Long postId);

    @GetMapping("/posts")
    List<Post> getPostByUserId(@RequestParam Long userId);

    @PostMapping("/posts")
    Post createPost(@RequestBody  Post post);

    @PutMapping("/posts")
    Post updatePost(Post post);

    @DeleteMapping("/posts/{postId}")
    Post deletePost(@PathVariable Long postId);
}
