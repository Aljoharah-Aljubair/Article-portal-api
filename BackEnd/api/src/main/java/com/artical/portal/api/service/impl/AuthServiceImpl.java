package com.artical.portal.api.service.impl;

import com.artical.portal.api.dto.AuthResponseDTO;
import com.artical.portal.api.dto.LoginDto;
import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.exceptions.EmailExistsException;
import com.artical.portal.api.exceptions.UsernameExistsException;
import com.artical.portal.api.mapppers.UserMapper;
import com.artical.portal.api.models.Privilege;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.security.JWTGenerator;
import com.artical.portal.api.security.UserDetailsServiceImpl;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
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
            throw new EmailExistsException(
                    "An account with the email address " + registerDto.getEmail() + " already exists.");
        }
        if (userRepository.findByUsername(registerDto.getUsername()) != null) {
            throw new UsernameExistsException(
                    "An account with the username " + registerDto.getUsername() + " already exists.");
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setMobileNumber(registerDto.getMobileNumber());
        user.setPrivileges((List<Privilege>) privilegeRepository.findByType("USER"));

        UserEntity userSaved = userRepository.save(user);
        return registerDto;

    }
    public String userRole(String username) {
        UserEntity user = userRepository.findByUsername(username);
        String role = user.getPrivileges().stream()
                .map(Privilege::getType)
                .collect(Collectors.joining(", ")); // Assuming user can have multiple roles   .map(Privilege::getType) // Convert each Privilege to its type description.
        return  role;
    }

}
