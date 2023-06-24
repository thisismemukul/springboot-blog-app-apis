package com.thismsmemukul.blogappapis.services;

import com.thismsmemukul.blogappapis.paylodes.CategoryDto;
import com.thismsmemukul.blogappapis.paylodes.UserDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category, UUID categoryId);
    CategoryDto getCategoryById(UUID categoryId);
    CategoryDto getCategoryByTitle(String title);
    List<CategoryDto> getAllCategories();
    void deleteCategory(UUID categoryId);
}
