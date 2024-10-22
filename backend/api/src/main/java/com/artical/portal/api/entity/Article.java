package com.artical.portal.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(length = 500)
    private String body;

    @ManyToOne
    @JoinColumn(name="authorId",nullable = false)
    private  UserEntity author;

    private String authorUsername;
    private String createdAt;
    private int numberOfLikes;
    private int numberOfDislikes;
    private boolean disabled;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name="article_images",
    joinColumns = {
            @JoinColumn(name = "article_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "image_id")
    }
    )
    private Set<Image> articleImages;


    public Set<Image> getArticleImages() {
        return articleImages;
    }

    public void setArticleImages(Set<Image> articleImages) {
        this.articleImages = articleImages;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public UserEntity getAuthor() {
        return author;
    }
    public void setAuthor(UserEntity author) {
        this.author = author;
    }
    public String getAuthorUsername() {
        return authorUsername;
    }
    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public int getNumberOfLikes() {
        return numberOfLikes;
    }
    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }
    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    public List<Comment> getComments() {
        return comments;
    }
}