package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "CategoryDto Model Information"
)
public class CategoryDto {//transfer the data b/w client and server

    private long id;
    @Schema(
            description = "Blog Category name"
    )
    private String name;
    @Schema(
            description = "Blog Category description"
    )
    private String description;
}
