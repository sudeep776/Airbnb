package com.springboot.project.airBnbApp.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
}
