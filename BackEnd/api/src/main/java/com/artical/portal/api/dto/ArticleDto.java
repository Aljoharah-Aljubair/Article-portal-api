package com.artical.portal.api.dto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private int id;
    @Size( max = 100, message = "Title cannot exceed {max} characters")
    private String title;
    @Size( max = 500, message = "Body cannot exceed {max} characters")
    private String body;
    private int authorId;
    private String authorUsername;
    private String createdAt;
    private int numberOfLikes;
    private int numberOfDislikes;
    private boolean disabled;
}
