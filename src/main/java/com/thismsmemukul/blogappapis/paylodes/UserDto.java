package com.thismsmemukul.blogappapis.paylodes;


import com.thismsmemukul.blogappapis.annotation.PasswordValueMatch;
import com.thismsmemukul.blogappapis.annotation.ValidatePassword;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private UUID id;
    @NotEmpty
    @Size(min = 3, message = "Name must be min of 3 characters !!")
    private String name;
    private String pic;
    @NotEmpty
    @Size(min = 3, message = "Username must be min of 3 characters !!")
    private String username;
    @NotEmpty(message = "Email is required !!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Please provide a valid email address")
    private String email;
    @NotEmpty(message = "Phone is required")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Please provide a valid phone number it should be of 10 digits")
    private String phone;

    @NotEmpty(message = "*Bio is required")
    private String bio;

    @NotNull
    @NotEmpty(message = "*Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$",message = "Password must be of 6 digits and should contains Capital,Small letters and digit.")
    @ValidatePassword
    private String password;
    @NotNull
    @NotEmpty(message = "*Confirm Password is required")
    @ValidatePassword
    private String confirmPassword;
    private int verified;
    private boolean active;
}
