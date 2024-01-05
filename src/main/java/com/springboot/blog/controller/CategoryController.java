package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //BUILD  ADD category REST API
    @Operation(
            summary = "Create Category REST APIs",
            description = "Created Category REST APIs is used to save Category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
   return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    //built Get category  rest api
    @Operation(
            summary = "Get Category REST APIs",
            description = "Get Category REST APIs is used to get single category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(
            @PathVariable("id") long categoryId
    ){
        CategoryDto categoryDto = categoryService.getCategory(categoryId);

        return  ResponseEntity.ok(categoryDto);
    }
    //Build get ALL rest api
    @Operation(
            summary = "Get ALL Category REST APIs",
            description = "Get ALL Category REST APIs is used to fetch all the Category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategories() );
    }

    //Build Update Category REST api
    @Operation(
            summary = "Updated Category REST APIs",
            description = "Update Category REST APIs is used to a particular category in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody  CategoryDto categoryDto,
            @PathVariable("id") long categoryId
    ){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId)
        );
    }

    //Build delete category rest api

    @Operation(
            summary = "Delete Category REST APIs",
            description = "Delete Category REST APIs is used to particular category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable("id") long categoryId){
        categoryService.deleteCategory(categoryId);
         return ResponseEntity.ok("Category deleted successfully !.");
    }

}
