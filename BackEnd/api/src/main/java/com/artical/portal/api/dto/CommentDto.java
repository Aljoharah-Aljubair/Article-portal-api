package com.artical.portal.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CommentDto {
        private int id;

        @Size(max = 100 , message = "Comment content must not exceed {max} characters")
        private String content;

        private int userId;
        private String userUsername;
        private int articleId;
        private String CreatedAT;
    }
