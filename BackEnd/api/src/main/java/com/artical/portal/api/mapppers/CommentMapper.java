package com.artical.portal.api.mapppers;

import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.Comment;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.artical.portal.api.models.UserEntity;


@Component
public class CommentMapper {
    private  final UserRepository userRepository;
    private  final ArticleRepository articleRepository;
    @Autowired
    public CommentMapper(UserRepository userRepository,ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository=articleRepository;
    }

    public CommentDto Comment2Dto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getUser().getId(),
                comment.getUserUsername(),
                comment.getArticle().getId(),
                comment.getCreatedAT());
    }
    public Comment CommentDto2Entity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getContent());

        // Fetch user from userRepository
        UserEntity author = userRepository.findByUsername(commentDto.getUserUsername());
                if(author==null)
                throw new IllegalArgumentException("Invalid Comment author ID: " + commentDto.getUserId());
        comment.setUser(author);
        comment.setUserUsername(comment.getUserUsername());

        // Fetch article from articleRepository
        Article article = articleRepository.findById(commentDto.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Article ID: " + commentDto.getArticleId()));
        comment.setArticle(article);

        comment.setCreatedAT(commentDto.getCreatedAT());

        return comment;
    }
}

