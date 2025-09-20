package com.fkrf.product_api.security;

import com.fkrf.product_api.model.User;
import com.fkrf.product_api.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {
    private final UUID id;
    private final String username;
    private final String password;
    private final boolean active;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPasswordHash();
        this.active = user.getIsActive();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()));
    }

    public UUID getId() {
        return id;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return active; }
}
