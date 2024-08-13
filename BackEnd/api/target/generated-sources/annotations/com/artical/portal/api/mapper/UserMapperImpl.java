package com.artical.portal.api.mapper;

import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.entity.Comment;
import com.artical.portal.api.entity.Privilege;
import com.artical.portal.api.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-13T11:56:59+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userEntity.getId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setMobileNumber( userEntity.getMobileNumber() );
        userDto.setPassword( userEntity.getPassword() );
        userDto.setEmail( userEntity.getEmail() );
        List<Privilege> list = userEntity.getPrivileges();
        if ( list != null ) {
            userDto.setPrivileges( new ArrayList<Privilege>( list ) );
        }
        List<Comment> list1 = userEntity.getComments();
        if ( list1 != null ) {
            userDto.setComments( new ArrayList<Comment>( list1 ) );
        }

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( userDto.getId() );
        userEntity.setUsername( userDto.getUsername() );
        userEntity.setMobileNumber( userDto.getMobileNumber() );
        userEntity.setPassword( userDto.getPassword() );
        userEntity.setEmail( userDto.getEmail() );
        List<Privilege> list = userDto.getPrivileges();
        if ( list != null ) {
            userEntity.setPrivileges( new ArrayList<Privilege>( list ) );
        }
        if ( userEntity.getComments() != null ) {
            List<Comment> list1 = userDto.getComments();
            if ( list1 != null ) {
                userEntity.getComments().addAll( list1 );
            }
        }

        return userEntity;
    }

    @Override
    public UserEntity toEntity(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( registerDto.getUsername() );
        userEntity.setMobileNumber( registerDto.getMobileNumber() );
        userEntity.setPassword( registerDto.getPassword() );
        userEntity.setEmail( registerDto.getEmail() );

        return userEntity;
    }
}
