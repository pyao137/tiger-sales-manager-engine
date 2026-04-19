package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.data.model.User;
import com.tigersalesmanager.engine.data.repo.BusinessRepository;
import com.tigersalesmanager.engine.data.repo.UserRepository;
import com.tigersalesmanager.engine.api.dto.BusinessRequestDTO;
import com.tigersalesmanager.engine.api.dto.BusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public BusinessResponseDTO createBusiness(BusinessRequestDTO businessDTO) {
        User owner = userRepository.findById(businessDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        Business business = Business.builder()
                .name(businessDTO.getName())
                .taxId(businessDTO.getTaxId())
                .owner(owner)
                .build();
        
        business = businessRepository.save(business);
        return mapToDTO(business);
    }

    public BusinessResponseDTO updateBusiness(UUID id, BusinessRequestDTO businessDTO) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        
        business.setName(businessDTO.getName());
        business.setTaxId(businessDTO.getTaxId());
        
        business = businessRepository.save(business);
        return mapToDTO(business);
    }

    public List<BusinessResponseDTO> listBusinessesByUser(UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        return businessRepository.findByOwner(owner).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BusinessResponseDTO getBusiness(UUID id) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return mapToDTO(business);
    }

    private BusinessResponseDTO mapToDTO(Business business) {
        return BusinessResponseDTO.builder()
                .id(business.getId())
                .name(business.getName())
                .taxId(business.getTaxId())
                .ownerId(business.getOwner().getId())
                .build();
    }
}
