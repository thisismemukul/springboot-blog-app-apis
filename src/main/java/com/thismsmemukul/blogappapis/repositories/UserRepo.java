package com.thismsmemukul.blogappapis.repositories;

import com.thismsmemukul.blogappapis.entities.User;
import com.thismsmemukul.blogappapis.paylodes.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);


}
