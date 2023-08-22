package com.thismsmemukul.blogappapis.controllers;

import com.thismsmemukul.blogappapis.paylodes.ApiResponse;
import com.thismsmemukul.blogappapis.paylodes.PostDto;
import com.thismsmemukul.blogappapis.services.PostService;
import lombok.extern.flogger.Flogger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;

    //POST - create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable UUID userId,
            @PathVariable UUID categoryId
    ) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //GET - get all Posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
    ) {
        List<PostDto> allPost = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    //GET - get Post By ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable UUID postId
    ) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //GET - get posts by User ID
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUserId(
            @PathVariable UUID userId
    ) {
        List<PostDto> getPostsByUserId = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(getPostsByUserId, HttpStatus.OK);
    }

    //GET - get posts by Category ID
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(
            @PathVariable UUID categoryId
    ) {
        List<PostDto> getPostsByCategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(getPostsByCategory, HttpStatus.OK);
    }

    //PUT - update Post by ID
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable UUID postId
    ) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }

    //DELETE - delete Post by ID
    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(
            @PathVariable UUID postId
    ) {
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!", true, HttpStatus.OK);
    }
}
