package com.artical.portal.api.controllers;

import java.util.List;
import com.artical.portal.api.dto.UserDto;
import com.artical.portal.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController

@CrossOrigin
public class UserController {

        private UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/users")
        public ResponseEntity<List<UserDto>> getAllEmployees(){
            return ResponseEntity.ok(userService.findAll());
        }

        @PreAuthorize("permitAll()")
        @PostMapping("/user")
        public ResponseEntity<UserDto>saveUser(@Valid @RequestBody  UserDto userDto) {
            return ResponseEntity.ok(userService.registerUser(userDto));
        }
}