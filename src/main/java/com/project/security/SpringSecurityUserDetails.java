package com.project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.project.models.User;

import java.util.Collection;
import java.util.Collections;

// Класс обёртка над сущностью User, который реализует интерфейс.
public class SpringSecurityUserDetails implements UserDetails {


    private final User user;

    public SpringSecurityUserDetails(User user) {
        this.user = user;
    }

    // Нужен для авторизации. Возвращает коллекцию тех прав, которые есть у пользователя.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
