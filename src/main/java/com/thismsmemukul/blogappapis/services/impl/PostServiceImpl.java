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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(page);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Post getpost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post ID", postId));
        return this.modelMapper.map(getpost, PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(UUID categoryId,Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
        Page<Post> postPageByCategory = this.postRepo.findByCategory(category,page);
        List<Post> allPosts=postPageByCategory.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPageByCategory.getNumber());
        postResponse.setPageSize(postPageByCategory.getSize());
        postResponse.setTotalPages(postPageByCategory.getTotalPages());
        postResponse.setTotalElements(postPageByCategory.getTotalElements());
        postResponse.setLastPage(postPageByCategory.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(UUID userId,Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User ID",userId));
        Page<Post> postPageByUser = this.postRepo.findByUser(user,page);
        List<Post> allPosts=postPageByUser.getContent();

        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPageByUser.getNumber());
        postResponse.setPageSize(postPageByUser.getSize());
        postResponse.setTotalPages(postPageByUser.getTotalPages());
        postResponse.setTotalElements(postPageByUser.getTotalElements());
        postResponse.setLastPage(postPageByUser.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
