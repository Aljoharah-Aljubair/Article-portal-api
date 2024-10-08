package com.artical.portal.api.service.impl;

import com.artical.portal.api.dto.*;
import com.artical.portal.api.exceptions.AlreadyExistsException;
import com.artical.portal.api.mapper.UserMapper;
import com.artical.portal.api.entity.Privilege;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CrossOrigin(origins = "http://localhost:4200/")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PrivilegeRepository privilegeRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PrivilegeRepository privilegeRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto registerUser(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new AlreadyExistsException(
                    "An account with the email address " + user.getEmail() + " already exists.");
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new AlreadyExistsException(
                    "An account with the username " + user.getUsername() + " already exists.");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPrivileges((List<Privilege>) privilegeRepository.findByType("USER"));
        userRepository.save(userMapper.toEntity(user));
        user.setId(userRepository.findByUsername(user.getUsername()).getId());
        return user;
    }
}