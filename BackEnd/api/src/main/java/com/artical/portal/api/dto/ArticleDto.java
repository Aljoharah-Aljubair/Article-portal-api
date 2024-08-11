package com.artical.portal.api.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private int id;

    @Size( max = 100, message = "Title cannot exceed {max} characters")
    private String Title;

    @Size( max = 500, message = "Body cannot exceed {max} characters")
    private String Body;

    private int authorId;
    private String authorUsername;
    private String CreatedAt;
    private int NumberOfLikes;
    private int NumberOfDislikes;
    private boolean Disabled;
}
