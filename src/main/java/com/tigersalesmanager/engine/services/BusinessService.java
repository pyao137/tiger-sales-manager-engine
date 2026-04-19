package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.data.model.User;
import com.tigersalesmanager.engine.data.repo.BusinessRepository;
import com.tigersalesmanager.engine.data.repo.UserRepository;
import com.tigersalesmanager.engine.domain.BusinessDomain;
import com.tigersalesmanager.engine.mappers.BusinessMapper;
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
    private final BusinessMapper businessMapper;

    public BusinessDomain createBusiness(BusinessDomain businessDomain) {
        User owner = userRepository.findById(businessDomain.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Business business = Business.builder()
                .name(businessDomain.getName())
                .taxId(businessDomain.getTaxId())
                .owner(owner)
                .build();

        business = businessRepository.save(business);
        return businessMapper.toDomain(business);
    }

    public BusinessDomain updateBusiness(UUID id, BusinessDomain businessDomain) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        business.setName(businessDomain.getName());
        business.setTaxId(businessDomain.getTaxId());

        business = businessRepository.save(business);
        return businessMapper.toDomain(business);
    }

    public List<BusinessDomain> listBusinessesByUser(UUID ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        return businessRepository.findByOwner(owner).stream()
                .map(businessMapper::toDomain)
                .collect(Collectors.toList());
    }

    public BusinessDomain getBusiness(UUID id) {
        Business business = businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return businessMapper.toDomain(business);
    }
}
