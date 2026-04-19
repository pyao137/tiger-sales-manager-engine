package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.User;
import com.tigersalesmanager.engine.data.repo.UserRepository;
import com.tigersalesmanager.engine.api.dto.UserRequestDTO;
import com.tigersalesmanager.engine.api.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .build();
        user = userRepository.save(user);
        return mapToDTO(user);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user = userRepository.save(user);
        return mapToDTO(user);
    }

    public UserResponseDTO getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
