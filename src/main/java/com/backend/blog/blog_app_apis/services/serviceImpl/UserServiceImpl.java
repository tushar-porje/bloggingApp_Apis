package com.backend.blog.blog_app_apis.services.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.blog_app_apis.entities.User;
import com.backend.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.backend.blog.blog_app_apis.payloads.UserDto;
import com.backend.blog.blog_app_apis.repositories.RoleRepo;
import com.backend.blog.blog_app_apis.repositories.UserRepo;
import com.backend.blog.blog_app_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=dtoToUser(userDto);
        User savedUser=userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=dtoToUser(userDto);
        user.getRoles().add(roleRepo.findByName("ROLE_USER").get());
        User savedUser=userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) throws ResourceNotFoundException{
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        //updating user object according to given userdto object to store in repo
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        //updating user entity with userId and updated fields
        User updatedUser=this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepo.findAll();
        return users.stream().map(user->userToDto(user)).toList();
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        return this.mapper.map(userDto, User.class);
    }
    private UserDto userToDto(User user){
        return this.mapper.map(user, UserDto.class);
    }

    
}
