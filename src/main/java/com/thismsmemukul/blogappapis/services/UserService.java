package com.thismsmemukul.blogappapis.services;

import com.thismsmemukul.blogappapis.paylodes.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, UUID userId);
    UserDto getUserById(UUID userId);
    UserDto getUserByUsername(String username);
    List<UserDto> getAllUsers();
    void deleteUser(UUID userId);

}
