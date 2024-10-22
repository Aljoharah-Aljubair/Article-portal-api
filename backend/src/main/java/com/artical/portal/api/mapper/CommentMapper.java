package com.artical.portal.api.mapper;

import com.artical.portal.api.dto.CommentDto;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "createdAT", target = "createdAT")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "article.id", target = "articleId")
    CommentDto toDto(Comment comment);


    @Mapping(source = "comment.id", target = "id")
    @Mapping(source = "comment.content", target = "content")
    @Mapping(source = "date", target = "createdAT")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "article", target = "article")
    Comment toEntity(CommentDto comment,String date,UserEntity user,Article article);
}
