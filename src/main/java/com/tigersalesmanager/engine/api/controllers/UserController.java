package com.tigersalesmanager.engine.api.controllers;

import com.tigersalesmanager.engine.api.dto.UserRequestDTO;
import com.tigersalesmanager.engine.api.dto.UserResponseDTO;
import com.tigersalesmanager.engine.domain.UserDomain;
import com.tigersalesmanager.engine.mappers.UserMapper;
import com.tigersalesmanager.engine.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        UserDomain domain = userMapper.toDomain(userDTO);
        UserDomain created = userService.createUser(domain);
        return ResponseEntity.ok(userMapper.toResponseDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO userDTO) {
        UserDomain domain = userMapper.toDomain(userDTO);
        UserDomain updated = userService.updateUser(id, domain);
        return ResponseEntity.ok(userMapper.toResponseDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) {
        UserDomain domain = userService.getUser(id);
        return ResponseEntity.ok(userMapper.toResponseDTO(domain));
    }
}
