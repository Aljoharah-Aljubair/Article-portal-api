package com.artical.portal.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private String createdAT;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;

    private String username;

    @ManyToOne
    @JoinColumn(name = "articleId",nullable = false)
    private Article article;

    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreatedAT() {
        return createdAT;
    }
    public void setCreatedAT(String createdAT) {
        this.createdAT = createdAT;
    }
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String userUsername) {
        this.username = userUsername;
    }
    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }
}
