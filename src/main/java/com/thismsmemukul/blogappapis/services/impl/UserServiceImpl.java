package com.thismsmemukul.blogappapis.services.impl;

import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.exceptions.ResourceNotFoundException;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.repositories.UserRepo;
import com.thismsmemukul.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
       User user = this.dtoToUser(userDto);
       User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDto getUserByUsername(UserDto username) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
    private User dtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPic(userDto.getPic());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setBio(userDto.getBio());
        user.setPassword(userDto.getPassword());
        user.setConfirmPassword(userDto.getConfirmPassword());
        return user;
    }
    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPic(user.getPic());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setBio(user.getBio());
        userDto.setPassword(user.getPassword());
        userDto.setConfirmPassword(user.getConfirmPassword());
        return userDto;
    }
}
