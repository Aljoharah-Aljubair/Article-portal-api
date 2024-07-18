package com.artical.portal.api.config;

import com.artical.portal.api.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

    private final UserDetailsServiceImpl myUserDetailsService;
    @Autowired
    public SecurityConfig(UserDetailsServiceImpl myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> {
                        authRequest.requestMatchers("/h2-console/**").permitAll();
                        authRequest.requestMatchers("/user","/admin").permitAll();
                        authRequest.requestMatchers("/login","/login/**").permitAll();
                        authRequest.requestMatchers("/logout").permitAll();
                        authRequest.requestMatchers(HttpMethod.GET,"/article/**","/article").permitAll();
                        authRequest.requestMatchers(HttpMethod.GET,"article/{id}/comment").permitAll();
                        authRequest.anyRequest().authenticated();
    })
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}

//========== comments [[17/7]]
// UserDetailsService userDetailsService;

//    @Autowired
//    public SecurityConfig(UserDetailsService userDetailsService{
//        this.userDetailsService = userDetailsService;
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }


//    @Bean
//    JdbcUserDetailsManager users(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }


//.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Ensure SessionCreationPolicy is imported
//.httpBasic(withDefaults())