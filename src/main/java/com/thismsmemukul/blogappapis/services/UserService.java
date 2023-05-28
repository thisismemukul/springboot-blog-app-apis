package com.thismsmemukul.blogappapis.services;

import com.thismsmemukul.blogappapis.paylodes.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Long userId);
    UserDto getUserById(Long userId);
    UserDto getUserByUsername(UserDto username);
    List<UserDto> getAllUsers();
    void deleteUser(Long userId);

}
