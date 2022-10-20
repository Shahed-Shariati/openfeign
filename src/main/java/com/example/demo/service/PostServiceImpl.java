package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.feing.PostFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostServiceImpl implements PostService{

   @Autowired
   private PostFeignClient postFeignClient;

    @Override
    public List<Post> getAllPosts()   {
        Gson gson = getGson();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = postFeignClient.getAllPosts();
        System.out.println(response.getStatusCode());
        try {
        List<Post> result = objectMapper.readValue(response.getBody(),new TypeReference<List<Post>>(){});
        return result;
        }catch (JsonProcessingException e){
            e.getStackTrace();
        }

      List<Post> result = Arrays.stream(gson.fromJson(response.getBody(),Post[].class)).toList();
      //return response.getBody();

        return null;
    }

    @Override
    public Post getPostById(Long postId) {
        return postFeignClient.getPostById(postId);
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return postFeignClient.createPost(post);
    }

    @Override
    public void updatePost(Long postId, Post post) {

    }

    @Override
    public void deletePost(Long postId) {

    }

    private Gson getGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
