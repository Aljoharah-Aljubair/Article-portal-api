package com.artical.portal.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CommentDto {
        private int id;
        @Size(max = 100 , message = "Comment content must not exceed {max} characters")
        private String content;
        private String createdAT;
        private int userId;
        private String username;
        private int articleId;
    }
