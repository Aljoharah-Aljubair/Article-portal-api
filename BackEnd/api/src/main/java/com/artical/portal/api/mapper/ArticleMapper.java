package com.artical.portal.api.mapper;


import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.username", target = "authorUsername")
    ArticleDto toDto(Article article);


    @Mapping(source = "articleDto.id", target = "id")
    @Mapping(source = "user.username", target = "authorUsername")
    @Mapping(source = "date", target = "createdAt")
    @Mapping(source = "user",target = "author")
    Article toEntity(ArticleDto articleDto, String date, UserEntity user);
}
