package com.artical.portal.api.mapper;

import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T11:56:59+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setId( comment.getId() );
        commentDto.setContent( comment.getContent() );
        commentDto.setCreatedAT( comment.getCreatedAT() );
        commentDto.setUserId( commentUserId( comment ) );
        commentDto.setUsername( commentUserUsername( comment ) );
        commentDto.setArticleId( commentArticleId( comment ) );

        return commentDto;
    }

    @Override
    public Comment toEntity(CommentDto comment, String date, UserEntity user, Article article) {
        if ( comment == null && date == null && user == null && article == null ) {
            return null;
        }

        Comment comment1 = new Comment();

        if ( comment != null ) {
            comment1.setId( comment.getId() );
            comment1.setContent( comment.getContent() );
        }
        if ( user != null ) {
            comment1.setUser( user );
            comment1.setUsername( user.getUsername() );
        }
        comment1.setCreatedAT( date );
        comment1.setArticle( article );

        return comment1;
    }

    private int commentUserId(Comment comment) {
        if ( comment == null ) {
            return 0;
        }
        UserEntity user = comment.getUser();
        if ( user == null ) {
            return 0;
        }
        int id = user.getId();
        return id;
    }

    private String commentUserUsername(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        UserEntity user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private int commentArticleId(Comment comment) {
        if ( comment == null ) {
            return 0;
        }
        Article article = comment.getArticle();
        if ( article == null ) {
            return 0;
        }
        int id = article.getId();
        return id;
    }
}
