package com.backend.blog.blog_app_apis.services;

import java.util.List;

import com.backend.blog.blog_app_apis.payloads.UserDto;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
 