package com.thismsmemukul.blogappapis.controllers;

import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // ResponseEntity class is used to represent the entire HTTP response, including the status code, headers, and the response body.
    //In the ResponseEntity<T> class, the type parameter T represents the type of the response body that is included in the ResponseEntity object.
    @GetMapping("/health")
    public String serviceHealthCheck(){
        return "Service UP";
    }
    //POST - Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    //GET - Read user
    //PUT - Update user
    //DELETE - Delete user
}
