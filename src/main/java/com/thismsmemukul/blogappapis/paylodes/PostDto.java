package com.thismsmemukul.blogappapis.paylodes;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {


    private UUID postId;
    @NotEmpty
    @Size(min = 3, message = "Title must be min of 3 characters !!")
    private String title;
    @NotEmpty(message = "Post content is required !!")
    @Size(message = "Post content should be of max 1000 characters !!")
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

}