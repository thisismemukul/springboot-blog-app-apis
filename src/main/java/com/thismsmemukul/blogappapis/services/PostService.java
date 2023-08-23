package com.thismsmemukul.blogappapis.services;

import com.thismsmemukul.blogappapis.paylodes.PostDto;
import com.thismsmemukul.blogappapis.paylodes.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, UUID userId, UUID categoryId);

    //update

    PostDto updatePost(PostDto postDto, UUID postId);

    // delete

    void deletePost(UUID postId);

    //get all posts

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);
//    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single post

    PostDto getPostById(UUID postId);

    //get all posts by category

    PostResponse getPostsByCategory(UUID categoryId,Integer pageNumber, Integer pageSize);

    //get all posts by user
    PostResponse getPostsByUser(UUID userId,Integer pageNumber, Integer pageSize);

    //search posts
    List<PostDto> searchPosts(String keyword);
}
