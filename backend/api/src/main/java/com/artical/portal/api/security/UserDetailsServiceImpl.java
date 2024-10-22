package com.artical.portal.api.security;

import com.artical.portal.api.entity.Privilege;
import com.artical.portal.api.entity.UserEntity;
import com.artical.portal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {//DONE


    private  UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {

        List<Privilege> privileges = user.getPrivileges();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Privilege privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+privilege.getType()));
        }
        return authorities;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        return new User(user.getUsername(),user.getPassword(),getAuthorities(user));
    }

}