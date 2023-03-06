package com.example.petproj.dto;

import com.example.petproj.model.UserRole;
import com.example.petproj.validator.PasswordMatches;
import com.example.petproj.validator.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *
 * @author Roman Netesa
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String secondName;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @ValidEmail
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private UserRole role;




}
