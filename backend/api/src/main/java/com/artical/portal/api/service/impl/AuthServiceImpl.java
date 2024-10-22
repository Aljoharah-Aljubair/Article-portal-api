package com.artical.portal.api.service.impl;

import com.artical.portal.api.dto.AuthResponseDTO;
import com.artical.portal.api.dto.LoginDto;
import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.exceptions.AlreadyExistsException;
import com.artical.portal.api.mapper.UserMapper;
//import com.artical.portal.api.mapppers.UserMapper;
import com.artical.portal.api.entity.Privilege;
import com.artical.portal.api.entity.UserEntity;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PrivilegeRepository privilegeRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                          PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder
                        ,UserMapper userMapper, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper=userMapper;
        this.jwtGenerator=jwtGenerator;
    }

    public AuthResponseDTO loginUser(LoginDto loginDto ){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }

    public RegisterDto registerUser(RegisterDto registerDto){
        if(userRepository.findByEmail(registerDto.getEmail())!=null){
            throw new AlreadyExistsException(
                    "An account with the email address " + registerDto.getEmail() + " already exists.");
        }
        if (userRepository.findByUsername(registerDto.getUsername()) != null) {
            throw new AlreadyExistsException(
                    "An account with the username " + registerDto.getUsername() + " already exists.");
        }
        UserEntity user = userMapper.toEntity(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setPrivileges(privilegeRepository.findByType("USER"));

        UserEntity userSaved = userRepository.save(user);
        return registerDto;

    }
    public String userRole(String username) {
        UserEntity user = userRepository.findByUsername(username);
        String role = user.getPrivileges().stream()
                .map(Privilege::getType)
                .collect(Collectors.joining(", "));
        return  role;
    }
}
