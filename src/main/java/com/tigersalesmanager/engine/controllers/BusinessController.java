package com.tigersalesmanager.engine.controllers;

import com.tigersalesmanager.engine.dto.BusinessRequestDTO;
import com.tigersalesmanager.engine.dto.BusinessResponseDTO;
import com.tigersalesmanager.engine.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessResponseDTO> createBusiness(@RequestBody BusinessRequestDTO businessDTO) {
        return ResponseEntity.ok(businessService.createBusiness(businessDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> updateBusiness(@PathVariable UUID id, @RequestBody BusinessRequestDTO businessDTO) {
        return ResponseEntity.ok(businessService.updateBusiness(id, businessDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> getBusiness(@PathVariable UUID id) {
        return ResponseEntity.ok(businessService.getBusiness(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BusinessResponseDTO>> listBusinessesByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(businessService.listBusinessesByUser(userId));
    }
}
