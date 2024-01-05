package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "RegistrationDto Model Information"
)
public class RegisterDto {

    @Schema(
            description = "Blog Registration name"
    )
    private String name;
    @Schema(
            description = "Blog Registration username"
    )
    private String username;
    @Schema(
            description = "Blog Registration email"
    )
    private String email;
    @Schema(
            description = "Blog Registration password"
    )
    private String password;
}
