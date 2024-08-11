package com.artical.portal.api.mapppers;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.exceptions.UserNotFoundException;
import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    private final UserRepository userRepository;
    @Autowired
    public ArticleMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Article toEntity(ArticleDto articleDto) {
        Article article = new Article();
        article.setCreatedAt(articleDto.getCreatedAt());
        article.setId(articleDto.getId());
        article.setBody(articleDto.getBody());
        article.setTitle(articleDto.getTitle());
        article.setNumberOfDislikes(articleDto.getNumberOfDislikes());
        article.setNumberOfLikes(articleDto.getNumberOfLikes());
        article.setDisabled(articleDto.isDisabled());

        UserEntity author = userRepository.findByUsername(articleDto.getAuthorUsername());
                if(author==null)
                    throw new UserNotFoundException("CANT FIND USER");
        article.setAuthor(author);
        article.setAuthorUsername(author.getUsername());
        return article;
    }
    public ArticleDto toDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setCreatedAt(article.getCreatedAt());
        articleDto.setBody(article.getBody());
        articleDto.setDisabled(article.isDisabled());
        articleDto.setTitle(article.getTitle());
        articleDto.setNumberOfDislikes(article.getNumberOfDislikes());
        articleDto.setNumberOfLikes(article.getNumberOfLikes());
        articleDto.setAuthorId(article.getAuthor().getId());
        articleDto.setAuthorUsername(article.getAuthorUsername());
        return articleDto;
    }
}