package com.artical.portal.api.service;

import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.models.UserEntity;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto user);
    public List<UserDto> findAll();
}
