package com.tigersalesmanager.engine.mappers;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.domain.BusinessDomain;
import com.tigersalesmanager.engine.domain.UserDomain;
import com.tigersalesmanager.engine.api.dto.BusinessRequestDTO;
import com.tigersalesmanager.engine.api.dto.BusinessResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessMapper {

    private final UserMapper userMapper;

    public BusinessDomain toDomain(Business entity) {
        return BusinessDomain.builder()
                .id(entity.getId())
                .name(entity.getName())
                .taxId(entity.getTaxId())
                .owner(userMapper.toDomain(entity.getOwner()))
                .build();
    }

    public Business toEntity(BusinessDomain domain) {
        return Business.builder()
                .id(domain.getId())
                .name(domain.getName())
                .taxId(domain.getTaxId())
                .owner(userMapper.toEntity(domain.getOwner()))
                .build();
    }

    public BusinessDomain toDomain(BusinessRequestDTO dto) {
        return BusinessDomain.builder()
                .name(dto.getName())
                .taxId(dto.getTaxId())
                .owner(UserDomain.builder().id(dto.getOwnerId()).build())
                .build();
    }

    public BusinessResponseDTO toResponseDTO(BusinessDomain domain) {
        return BusinessResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .taxId(domain.getTaxId())
                .ownerId(domain.getOwner().getId())
                .build();
    }
}
