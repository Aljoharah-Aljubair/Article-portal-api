package com.artical.portal.api.mapper;


import com.artical.portal.api.dto.ArticleDto;
import com.artical.portal.api.dto.ImageDto;
import com.artical.portal.api.entity.Article;
import com.artical.portal.api.entity.Image;
import com.artical.portal.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "articleImages", target = "articleImages")
    ArticleDto toDto(Article article);

    @Mapping(source = "articleDto.id", target = "id")
    @Mapping(source = "user.username", target = "authorUsername")
    @Mapping(source = "date", target = "createdAt")
    @Mapping(source = "user", target = "author")
    @Mapping(source = "articleDto.articleImages", target = "articleImages")
    Article toEntity(ArticleDto articleDto, String date, UserEntity user);

//    default List<ImageDto> toImageDtoList(Set<Image> images) {
//        return images.stream().map(image -> new ImageDto(image.getId(), image.getName(), image.getType(), image.getPicByte())).collect(Collectors.toList());
//    }
//
//    default Set<Image> toImageEntitySet(List<ImageDto> imageDtos) {
//        return imageDtos.stream().map(dto -> new Image(dto.getId(), dto.getName(), dto.getType(), dto.getPicByte())).collect(Collectors.toSet());
//    }
}