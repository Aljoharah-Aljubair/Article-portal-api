package com.artical.portal.api.config;

import com.artical.portal.api.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsServiceImpl myUserDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Be cautious with disabling CSRF
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/user", "/admin").permitAll()
                        .requestMatchers("/login", "/login/**").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers(HttpMethod.GET, "/article/**", "/article").permitAll()
                        .requestMatchers(HttpMethod.GET, "article/{id}/comment").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout((logout) ->
                        logout.logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )
                .headers(headers -> headers.frameOptions(frameoption -> frameoption.sameOrigin()));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
