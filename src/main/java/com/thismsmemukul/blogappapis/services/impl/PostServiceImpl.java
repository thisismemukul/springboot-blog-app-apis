package com.thismsmemukul.blogappapis.services.impl;

import com.thismsmemukul.blogappapis.entities.Category;
import com.thismsmemukul.blogappapis.entities.Post;
import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.exceptions.ResourceNotFoundException;
import com.thismsmemukul.blogappapis.paylodes.PostDto;
import com.thismsmemukul.blogappapis.paylodes.PostResponse;
import com.thismsmemukul.blogappapis.repositories.CategoryRepo;
import com.thismsmemukul.blogappapis.repositories.PostRepo;
import com.thismsmemukul.blogappapis.repositories.UserRepo;
import com.thismsmemukul.blogappapis.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, UUID userId, UUID categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, UUID postId) {
        Post getpost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        getpost.setTitle(postDto.getTitle());
        getpost.setContent(postDto.getContent());
        getpost.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(getpost);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(UUID postId) {
        Post getpost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        this.postRepo.delete(getpost);
    }

    @Override
//    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
    public List<PostDto> getAllPost() {
        List<Post> allPosts = this.postRepo.findAll();
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Post getpost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        return this.modelMapper.map(getpost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(UUID categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
        List<Post> postsbyCategory = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = postsbyCategory.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(UUID userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));
        List<Post> postsbyUser = this.postRepo.findByUser(user);
        List<PostDto> postDtos = postsbyUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
