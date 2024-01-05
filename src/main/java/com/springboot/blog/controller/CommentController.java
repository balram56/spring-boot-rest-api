package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @Operation(
            summary = "Create Comment REST APIs",
            description = "Created Comment REST APIs is used to save comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto
    ){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    // for get
    @Operation(
            summary = "Get ALL Comment REST APIs",
            description = "Get ALL Comment REST APIs is used to fetch all the comment from the database basis postId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(
            @PathVariable(value = "postId") long postId){
        return commentService.getCommentsBypostId(postId);

    }

    @Operation(
            summary = "Get Comment REST APIs",
            description = "Get Comment REST APIs is used to get single post and commentId from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsByPostId(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId
    ){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
    return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    //get comment by id and  postid
    //http://localhost:8080/api/posts/2/comments/2
    @GetMapping("/posts/{postId}/coments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return  new ResponseEntity<>(commentDto,HttpStatus.OK);

    }
    //Update comment by id
    //http://localhost:8080/api/posts/2/comments/1
    @Operation(
            summary = "Updated Comment REST APIs",
            description = "Update Comment REST APIs is used to a particular comment in the database on the basis of postId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
@PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updatateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId,
           @Valid @RequestBody CommentDto commentDto
    ){
    CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
}
//delete by id
  //  http://localhost:8080/api/posts/2/comments/2
@Operation(
        summary = "Delete Comment REST APIs",
        description = "Delete Comment REST APIs is used to particular comment from the database"
)
@ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
)
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
           @PathVariable(value = "postId") long postId,
           @PathVariable(value = "id") long commentId

    ){
        commentService.deleteComment(postId, commentId);

        return new ResponseEntity<>("comment is deleted successfully !!", HttpStatus.OK);
    }


}
