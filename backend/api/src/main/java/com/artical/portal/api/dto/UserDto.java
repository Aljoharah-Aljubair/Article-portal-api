package com.artical.portal.api.dto;

import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.Privilege;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotBlank(message = "Username must not be blank")
    private String username;
    private int mobileNumber;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
    private List<Privilege> privileges;
    private List<Comment> comments;
}
