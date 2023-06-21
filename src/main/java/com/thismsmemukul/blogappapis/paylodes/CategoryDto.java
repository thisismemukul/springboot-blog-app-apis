package com.thismsmemukul.blogappapis.paylodes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


    private UUID categoryId;

    @NotEmpty(message = "Category Title is required !!")
    @Size(min = 2, message = "Category title must be min of 2 characters !!")
    private String categoryTitle;
    @NotEmpty(message = "Category Description is required !!")
    @Size(max = 100, message = "Category Description should be of max 100 characters !!")
    private String categoryDescription;
}
