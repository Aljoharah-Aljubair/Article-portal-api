package com.artical.portal.api.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {//DONE

    private UserEntity user ;
    @Autowired
    public UserDetailsImpl(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Privilege> privileges = user.getPrivileges();
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Privilege privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+privilege.getType()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}