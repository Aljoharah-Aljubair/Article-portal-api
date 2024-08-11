package com.artical.portal.api;

import com.artical.portal.api.models.Privilege;
import com.artical.portal.api.models.UserEntity;
import com.artical.portal.api.repository.PrivilegeRepository;
import com.artical.portal.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;

@CrossOrigin(origins = "http://localhost:4200/")
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.artical.portal.api.repository" })
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	}

