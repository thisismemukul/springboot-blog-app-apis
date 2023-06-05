package com.thismsmemukul.blogappapis.controllers;

import com.thismsmemukul.blogappapis.paylodes.ApiResponse;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    //GET - Read users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    //GET - Read user By Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("userId") UUID userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
    //GET - Read user By username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }
    //PUT - Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") UUID userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }
    //DELETE - Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") UUID userId){
       this.userService.deleteUser(userId);
       return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true,HttpStatus.OK), HttpStatus.OK);
    }
}
