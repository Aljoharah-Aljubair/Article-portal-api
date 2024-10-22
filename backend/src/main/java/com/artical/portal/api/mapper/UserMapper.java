package com.artical.portal.api.mapper;

import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userDto);

    UserEntity toEntity(RegisterDto registerDto);
}
