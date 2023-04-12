package com.java.springboot.controller;

import com.java.springboot.dto.UserDto;
import com.java.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for user Resource",
        description = "CRUD REST APIs - Create User, Update User, Get All User, Delete User"
)
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;


    // build create User REST API
    // http://localhost:8080/api/users
    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201 CREATE"
    )
    @PostMapping("addUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    // Build Get All User REST API
    // http://localhost:8080/api/users
    @Operation(
            summary = "Get All User REST API",
            description = "Get All User REST API is used to get a all the user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    // http://localhost:8080/api/users/1
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                              @RequestBody @Valid UserDto user) {
        user.setId(userId);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    // http://localhost:8080/api/users/1
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("delete user successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    // build getUserById REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }

}
