package com.thismsmemukul.blogappapis.repositories;

import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);


}
