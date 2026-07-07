package com.project.authentication.service;

import com.project.authentication.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImple implements UserDetails {

    private Long id;
    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    UserDetailsImple(Long id, String username,String password)
    {
        this.id = id;
        this.username=username;
        this.password=password;
        this.authorities = List.of(new SimpleGrantedAuthority("self"));
    }

    public static UserDetailsImple build(User user)
    {
        return new UserDetailsImple(user.getId(),user.getUsername(),user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
