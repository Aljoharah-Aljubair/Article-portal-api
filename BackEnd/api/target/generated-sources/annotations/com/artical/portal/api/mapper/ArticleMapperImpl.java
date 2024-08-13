package com.artical.portal.api.mapper;

import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.UserEntity;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T11:56:59+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleDto toDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDto articleDto = new ArticleDto();

        articleDto.setAuthorId( articleAuthorId( article ) );
        articleDto.setAuthorUsername( articleAuthorUsername( article ) );
        articleDto.setId( article.getId() );
        articleDto.setTitle( article.getTitle() );
        articleDto.setBody( article.getBody() );
        articleDto.setCreatedAt( article.getCreatedAt() );
        articleDto.setNumberOfLikes( article.getNumberOfLikes() );
        articleDto.setNumberOfDislikes( article.getNumberOfDislikes() );
        articleDto.setDisabled( article.isDisabled() );

        return articleDto;
    }

    @Override
    public Article toEntity(ArticleDto articleDto, String date, UserEntity user) {
        if ( articleDto == null && date == null && user == null ) {
            return null;
        }

        Article article = new Article();

        if ( articleDto != null ) {
            article.setId( articleDto.getId() );
            article.setTitle( articleDto.getTitle() );
            article.setBody( articleDto.getBody() );
            article.setNumberOfLikes( articleDto.getNumberOfLikes() );
            article.setNumberOfDislikes( articleDto.getNumberOfDislikes() );
            article.setDisabled( articleDto.isDisabled() );
        }
        if ( user != null ) {
            article.setAuthorUsername( user.getUsername() );
            article.setAuthor( user );
            if ( article.getComments() != null ) {
                List<Comment> list = user.getComments();
                if ( list != null ) {
                    article.getComments().addAll( list );
                }
            }
        }
        article.setCreatedAt( date );

        return article;
    }

    private int articleAuthorId(Article article) {
        if ( article == null ) {
            return 0;
        }
        UserEntity author = article.getAuthor();
        if ( author == null ) {
            return 0;
        }
        int id = author.getId();
        return id;
    }

    private String articleAuthorUsername(Article article) {
        if ( article == null ) {
            return null;
        }
        UserEntity author = article.getAuthor();
        if ( author == null ) {
            return null;
        }
        String username = author.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
