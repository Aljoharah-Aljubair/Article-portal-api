package com.artical.portal.api.mapppers;

import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.models.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public  UserDto UserEntity2Dto(UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getPrivileges().stream().toList(),
                user.getComments().stream().toList()
        );
    }

    public static UserEntity UserDto2Entity(UserDto user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getMobileNumber(),
                user.getPassword(),
                user.getEmail(),
                user.getPrivileges() != null ? user.getPrivileges().stream().collect(Collectors.toList()) : null,
                user.getComments() != null ? user.getComments().stream().collect(Collectors.toList()) : null
        );
    }
}