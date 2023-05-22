package com.thismsmemukul.blogappapis.entities;

import com.thismsmemukul.blogappapis.annotation.PasswordValueMatch;
import com.thismsmemukul.blogappapis.annotation.ValidatePassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    @NotBlank(message = "*Please provide your name")
    private String name;

    private String pic;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "*Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Username must be alphanumeric and between 3 to 20 characters")
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "*Email is required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Please provide a valid email address")
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    @NotBlank(message = "*Phone is required")
    @Size(min=0,max=10,message = "Number should have 10 digits")
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$",message = "Please provide a valid phone number")
    private String phone;

    @Column(name = "bio", length = 100)
    @NotBlank(message = "*Bio is required")
    private String bio;

    @Column(name = "password", nullable = false)
    @ValidatePassword
    @NotNull
    @NotBlank(message = "*Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$",message = "Password must be of 6 digits and should contains Capital,Small letters and digit.")
    private String password;

    @Column(name = "confirm_password", nullable = false)
    @ValidatePassword
    @NotNull
    @NotBlank(message = "*Confirm Password is required")
    private String confirmPassword;

    @Column(name = "verified", nullable = false)
    private int verified;

    @Column(name = "active", nullable = false)
    private boolean active;
}
