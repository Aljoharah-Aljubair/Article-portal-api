package com.artical.portal.api.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String email;
    private int mobileNumber;
}
