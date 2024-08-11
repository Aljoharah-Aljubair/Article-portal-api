package com.artical.portal.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String Text;
    private String CreatedAT;
     @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user;

    String userUsername;

    @ManyToOne
    @JoinColumn(name = "article_id",nullable = false)
    private Article article;
}
