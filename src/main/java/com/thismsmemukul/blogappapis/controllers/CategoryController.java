package com.thismsmemukul.blogappapis.controllers;

import com.thismsmemukul.blogappapis.paylodes.ApiResponse;
import com.thismsmemukul.blogappapis.paylodes.CategoryDto;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //POST create Category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    //GET All Categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
    //GET - Read category By ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryByID(@PathVariable("categoryId") UUID categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }
    //GET - Read category By title
    @GetMapping("/title/{title}")
    public ResponseEntity<CategoryDto> getCategoryByTitle(@PathVariable("title") String title){
        return ResponseEntity.ok(this.categoryService.getCategoryByTitle(title));
    }
    //PUT - Update Category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") UUID categoryId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }
    //DELETE - Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") UUID categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true,HttpStatus.OK), HttpStatus.OK);
    }
}
