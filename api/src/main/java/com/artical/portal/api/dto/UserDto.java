package com.artical.portal.api.dto;

import com.artical.portal.api.models.Comment;
import com.artical.portal.api.models.Privilege;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
