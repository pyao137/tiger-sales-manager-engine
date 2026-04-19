package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.User;
import com.tigersalesmanager.engine.data.repo.UserRepository;
import com.tigersalesmanager.engine.domain.UserDomain;
import com.tigersalesmanager.engine.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDomain createUser(UserDomain userDomain) {
        User user = userMapper.toEntity(userDomain);
        user = userRepository.save(user);
        return userMapper.toDomain(user);
    }

    public UserDomain updateUser(UUID id, UserDomain userDomain) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDomain.getName());
        user.setEmail(userDomain.getEmail());
        user = userRepository.save(user);
        return userMapper.toDomain(user);
    }

    public UserDomain getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDomain(user);
    }
}
