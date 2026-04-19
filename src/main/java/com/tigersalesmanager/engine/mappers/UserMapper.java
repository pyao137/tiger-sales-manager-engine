package com.tigersalesmanager.engine.mappers;

import com.tigersalesmanager.engine.data.model.User;
import com.tigersalesmanager.engine.domain.UserDomain;
import com.tigersalesmanager.engine.api.dto.UserRequestDTO;
import com.tigersalesmanager.engine.api.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDomain toDomain(User entity) {
        return UserDomain.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .build();
    }

    public User toEntity(UserDomain domain) {
        return User.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .name(domain.getName())
                .build();
    }

    public UserDomain toDomain(UserRequestDTO dto) {
        return UserDomain.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public UserResponseDTO toResponseDTO(UserDomain domain) {
        return UserResponseDTO.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .name(domain.getName())
                .build();
    }
}
