package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class PostDtoV2 {
    private long id;
    //title should not be null or empty
    //title should have at least 2 characers
    @NotEmpty
    @Size(min = 2, message = "Post title should have 2 characters")
    @Schema(
            description = "Blog Post title"
    )
    private String title;
    //post description should be not null or empty
    //post description should have at least 10 charACTERS
    @NotEmpty
    @Size(min = 10, message = "post description should have atleast 10 characters")
    @Schema(
            description = "Blog Post description"
    )
    private String description;
    //post content should not be null or empty
    @NotEmpty
    @Schema(
            description = "Blog Post content"
    )
    private String content;

    private Set<CommentDto> comments;

    //Add post used to category
    @Schema(
            description = "Blog Post category"
    )
    private long categoryId;

    private List<String> tags;
}


