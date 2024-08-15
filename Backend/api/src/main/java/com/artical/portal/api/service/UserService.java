package com.artical.portal.api.service;

import com.artical.portal.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto user);
    public List<UserDto> findAll();
}
