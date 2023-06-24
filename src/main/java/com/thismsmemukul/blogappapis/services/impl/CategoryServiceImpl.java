package com.thismsmemukul.blogappapis.services.impl;

import com.thismsmemukul.blogappapis.entities.Category;
import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.exceptions.ResourceNotFoundException;
import com.thismsmemukul.blogappapis.paylodes.CategoryDto;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.repositories.CategoryRepo;
import com.thismsmemukul.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, UUID categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = this.categoryRepo.save(category);
        CategoryDto updatedCategory = this.categoryToDto(savedCategory);
        return updatedCategory;
    }

    @Override
    public CategoryDto getCategoryById(UUID categoryId) {
    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
    return this.categoryToDto(category);
    }

    @Override
    public CategoryDto getCategoryByTitle(String categoryDto) {
        Category category = this.categoryRepo.findBycategoryTitle(categoryDto).orElseThrow(()-> new ResourceNotFoundException("Category","Id",UUID.randomUUID()));
        return this.categoryToDto(category);
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = allCategories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(UUID categoryId) {
    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
    this.categoryRepo.delete(category);
    }
    private Category dtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto,Category.class);
        return category;
    }
    private CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }
}
