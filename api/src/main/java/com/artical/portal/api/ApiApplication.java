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

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = { "com.artical.portal.api.repository" })
public class ApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PrivilegeRepository privilegeRepository, UserRepository userRepository) {
		return args -> {
			Privilege userPrivilege = new Privilege(1, "USER");
			Privilege adminPrivilege = new Privilege(2, "ADMIN");
			privilegeRepository.save(userPrivilege);
			privilegeRepository.save(adminPrivilege);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			UserEntity user = new UserEntity(1, "Aljoharah", 54227267, bCryptPasswordEncoder.encode("123"), "Aljoharah@gmail.com", Arrays.asList(adminPrivilege, userPrivilege), new ArrayList<>());
			UserEntity user1 = new UserEntity(2, "Noura", 56461050, bCryptPasswordEncoder.encode("123"), "Noura@gmail.com", Arrays.asList(adminPrivilege), new ArrayList<>());

			userRepository.save(user);
			userRepository.save(user1);


		};
	}
}
