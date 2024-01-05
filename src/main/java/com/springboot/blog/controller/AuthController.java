package com.springboot.blog.controller;

import com.springboot.blog.payload.JWTAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "CRUD REST APIs for Auth Resource"
)
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    //BUILD LOGIN REST api


    @Operation(
            summary = "Login Auth REST APIs",
            description = "Login Auth REST APIs is used to login Auth into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 CREATED"
    )
    @PostMapping(value = {"/login", "/signing"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

       JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
      jwtAuthResponse.setAccessToken(token);

   return  ResponseEntity.ok(jwtAuthResponse);
    }

    //Build Register Rest API

    @Operation(
            summary = "Create Auth REST APIs",
            description = "Created Auth REST APIs is used to save Auth into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
