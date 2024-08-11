package com.artical.portal.api.controllers;

import com.artical.portal.api.dto.AuthResponseDTO;
import com.artical.portal.api.dto.LoginDto;
import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.exceptions.EmailExistsException;
import com.artical.portal.api.exceptions.UsernameExistsException;
import com.artical.portal.api.models.Privilege;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import com.artical.portal.api.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {

        this.authService = authService;
    }
    @PostMapping("register")
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.registerUser(registerDto));
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.loginUser(loginDto));
    }

    @GetMapping("/role")
    public ResponseEntity<Map<String, String>> userRole(@RequestParam String username){
        String role = authService.userRole(username);
        Map<String, String> roleMap = new HashMap<>();
        roleMap.put("role", role);
        return ResponseEntity.ok(roleMap);
    }
}