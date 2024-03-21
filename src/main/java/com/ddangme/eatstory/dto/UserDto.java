package com.ddangme.eatstory.dto;

import com.ddangme.eatstory.domain.User;
import com.ddangme.eatstory.domain.UserRole;
import com.ddangme.eatstory.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private UserStatus userStatus;
    private UserRole userRole;
    private String img;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime deletedAt;

    private UserDto(Long id, String userId, String password, String nickname) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }


    public static UserDto fromEntity(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUserId(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getUserStatus(),
                entity.getUserRole(),
                entity.getImg(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getDeletedAt()
        );
    }

    public static UserDto of(Long id, String userId, String password, String nickname) {
        return new UserDto(id, userId, password, nickname);
    }

}
