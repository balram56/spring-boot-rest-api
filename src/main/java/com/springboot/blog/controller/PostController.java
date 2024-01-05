package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST APIs",
            description = "Created Post REST APIs is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    //create blog post Rest Api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost( @Valid  @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
    //get all posts rest api

    //http://localhost:8080/api/posts?pageNo=0&pagesize=10&sortBy=title&sortDir=asc

    @Operation(
            summary = "Get ALL Post REST APIs",
            description = "Get ALL Post REST APIs is used to fetch all the post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    //get post by id
    @Operation(
            summary = "Get Post REST APIs",
            description = "Get Post REST APIs is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(
            @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //updated post by id rest api
    @Operation(
            summary = "Updated Post REST APIs",
            description = "Update Post REST APIs is used to a particular post in the database"
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
    public ResponseEntity<PostDto> updatePost(
           @Valid @RequestBody PostDto postDto,
            @PathVariable(name = "id") long id

    ){
    PostDto updatePost = postService.updatePost(postDto, id);
    return new ResponseEntity<>(updatePost, HttpStatus.OK);
}
//delete post rest api
@Operation(
        summary = "Delete Post REST APIs",
        description = "Delete Post REST APIs is used to particular post from the database"
)
@ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
)
@SecurityRequirement(
        name = "Bearer Authentication"
)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable(name = "id") long id
    ){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity deleted successfully", HttpStatus.OK);
    }

   //Build Get Posts by Category REST API
    //http://localhost:8080/api/posts/category/3

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable("id") long categoryId
    ){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return  ResponseEntity.ok(postDtos);
    }

}

