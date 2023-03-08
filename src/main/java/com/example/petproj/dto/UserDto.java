package com.example.petproj.dto;

import com.example.petproj.model.UserRole;
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
    private UserRole role;

    public UserDto(String name, String secondName, String phone, String email, String password, UserRole role) {
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
