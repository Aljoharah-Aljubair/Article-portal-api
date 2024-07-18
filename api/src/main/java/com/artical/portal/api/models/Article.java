package com.artical.portal.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String Title;
    @Column(length = 500)//for length of 500
    private String Body;
    @ManyToOne
    @JoinColumn(name="author_id",nullable = false)
    private  UserEntity Author;

    private String authorUsername;
    private String CreatedAt;
    private int NumberOfLikes;
    private int NumberOfDislikes;
    private boolean Disabled;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
}