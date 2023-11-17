package com.backend.blog.blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.blog_app_apis.payloads.ApiResponse;
import com.backend.blog.blog_app_apis.payloads.UserDto;
import com.backend.blog.blog_app_apis.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    UserService userService;

    //post(/)
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto=this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createdUserDto,HttpStatus.CREATED);
    }

    //get(/userId)
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto=this.userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    //get(/)
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDto=this.userService.getAllUsers();
        return ResponseEntity.ok(usersDto);
    }

    //put(/userid)
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUserDto=this.userService.updateUser(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
    }

    //delete(/userId)
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User of id: " +userId +" deleted successfully",true));
    }
}
