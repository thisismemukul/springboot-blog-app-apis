package com.thismsmemukul.blogappapis.paylodes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private UUID id;
    private String name;
    private String pic;
    private String username;
    private String email;
    private String phone;
    private String bio;
    private String password;
    private String confirmPassword;
    private int verified;
    private boolean active;
}
