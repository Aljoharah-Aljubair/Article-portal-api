package com.artical.portal.api.controllers;

import com.artical.portal.api.models.Article;
import com.artical.portal.api.models.Comment;
import com.artical.portal.api.models.Privilege;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.ArticleRepository;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbInit {

    private UserRepository userRepository;
    private PrivilegeRepository privilegeRepository;
    private ArticleRepository articleRepository;
    @Autowired
    public DbInit(UserRepository userRepository, PrivilegeRepository privilegeRepository,ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.articleRepository=articleRepository;
    }
    @PostConstruct
    private void postConstruct() {
        Privilege userPrivilege = new Privilege(1, "USER");
        Privilege adminPrivilege = new Privilege(2, "ADMIN");
        privilegeRepository.save(userPrivilege);
        privilegeRepository.save(adminPrivilege);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserEntity admin_user = new UserEntity(1, "Admin_User", 54227267, bCryptPasswordEncoder.encode("123"), "AdminAndUser@gmail.com", Arrays.asList(adminPrivilege, userPrivilege), new ArrayList<>());
        UserEntity admin = new UserEntity(2, "Admin", 56461050, bCryptPasswordEncoder.encode("123"), "Admin@gmail.com", Arrays.asList(adminPrivilege), new ArrayList<>());
        UserEntity user = new UserEntity(3, "User", 56461050, bCryptPasswordEncoder.encode("123"), "User@gmail.com", Arrays.asList(userPrivilege), new ArrayList<>());

        userRepository.save(admin_user);
        userRepository.save(admin);
        userRepository.save(user);

    }
}

