package com.artical.portal.api.service;

import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.models.UserEntity;

public interface UserService {
    UserDto registerUser(UserDto user);
}
