package com.goldenladder.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;

    private String password;

    private String repeatPassword;

    private String email;

    private String birthday;

    public UserDto(String username, String password, String email, String birthday) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.repeatPassword=null;
    }
}