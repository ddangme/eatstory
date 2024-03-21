package com.ddangme.eatstory.dto;

import com.ddangme.eatstory.domain.UserRole;
import com.ddangme.eatstory.domain.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record UserPrincipal(
        Long id,
        String userId,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String nickname,
        UserStatus userStatus
) implements UserDetails {

    public static UserPrincipal of(Long id, String userId, String password, String nickname, UserStatus userStatus) {
        return new UserPrincipal(id, userId, password,
                Set.of(UserRole.USER).stream()
                        .map(UserRole::getType)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
                , nickname, userStatus);
    }

    public static UserPrincipal fromDto(UserDto dto) {
        return UserPrincipal.of(
                dto.getId(),
                dto.getUserId(),
                dto.getPassword(),
                dto.getNickname(),
                dto.getUserStatus()
        );
    }


    @Override public String getUsername() { return userId; }
    @Override public String getPassword() { return password; }
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
