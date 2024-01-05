package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "CommentDto Model Information"
)
public class CommentDto {
    private long id;
    //name should be not null or empty
    @NotEmpty(message = "name should be not null or empty")
    @Schema(
            description = "Blog Comment name"
    )
    private String name;
    //email should be not null or empty
    //email field validation
    @NotEmpty(message = "email should be not null or empty")
    @Email
    @Schema(
            description = "Blog Comment email "
    )
    private String email;
    //Comment body should be not nul or empty
    //Comment body must be minimum 10 characters
    @NotEmpty
    @Size(message = "Comment body must be minimum 10 characters")
    @Schema(
            description = "Blog Comment body"
    )
    private String body;

}
