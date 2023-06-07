package com.thismsmemukul.blogappapis.services.impl;

import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.exceptions.ResourceNotFoundException;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import com.thismsmemukul.blogappapis.repositories.UserRepo;
import com.thismsmemukul.blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
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
    public UserDto updateUser(UserDto userDto, UUID userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setPic(userDto.getPic());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setBio(userDto.getBio());
        user.setPassword(userDto.getPassword());
        User saveUser = this.userRepo.save(user);
        UserDto updatedUser = this.userToDto(saveUser);
        return updatedUser;
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public UserDto getUserByUsername(String userDto) {
        User user1 = this.userRepo.findByUsername(userDto).orElseThrow(() ->new ResourceNotFoundException("User", "Id", UUID.randomUUID()));
        return this.userToDto(user1);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> allusers = this.userRepo.findAll();
        List<UserDto> userDtosList = allusers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtosList;
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
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
