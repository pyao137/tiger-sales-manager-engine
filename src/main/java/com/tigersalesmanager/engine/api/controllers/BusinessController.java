package com.tigersalesmanager.engine.api.controllers;

import com.tigersalesmanager.engine.api.dto.BusinessRequestDTO;
import com.tigersalesmanager.engine.api.dto.BusinessResponseDTO;
import com.tigersalesmanager.engine.domain.BusinessDomain;
import com.tigersalesmanager.engine.mappers.BusinessMapper;
import com.tigersalesmanager.engine.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final BusinessMapper businessMapper;

    @PostMapping
    public ResponseEntity<BusinessResponseDTO> createBusiness(@RequestBody BusinessRequestDTO businessDTO) {
        BusinessDomain domain = businessMapper.toDomain(businessDTO);
        BusinessDomain created = businessService.createBusiness(domain);
        return ResponseEntity.ok(businessMapper.toResponseDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> updateBusiness(@PathVariable UUID id, @RequestBody BusinessRequestDTO businessDTO) {
        BusinessDomain domain = businessMapper.toDomain(businessDTO);
        BusinessDomain updated = businessService.updateBusiness(id, domain);
        return ResponseEntity.ok(businessMapper.toResponseDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> getBusiness(@PathVariable UUID id) {
        BusinessDomain domain = businessService.getBusiness(id);
        return ResponseEntity.ok(businessMapper.toResponseDTO(domain));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BusinessResponseDTO>> listBusinessesByUser(@PathVariable UUID userId) {
        List<BusinessDomain> domains = businessService.listBusinessesByUser(userId);
        return ResponseEntity.ok(domains.stream()
                .map(businessMapper::toResponseDTO)
                .collect(Collectors.toList()));
    }
}
