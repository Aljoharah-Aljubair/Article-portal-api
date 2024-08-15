package com.artical.portal.api.controllers;

import com.artical.portal.api.dto.AuthResponseDTO;
import com.artical.portal.api.dto.LoginDto;
import com.artical.portal.api.dto.RegisterDto;
import com.artical.portal.api.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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