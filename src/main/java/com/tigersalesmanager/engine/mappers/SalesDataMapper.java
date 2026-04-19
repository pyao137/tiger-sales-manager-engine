package com.tigersalesmanager.engine.mappers;

import com.tigersalesmanager.engine.data.model.SalesData;
import com.tigersalesmanager.engine.domain.BusinessDomain;
import com.tigersalesmanager.engine.domain.SalesDataDomain;
import com.tigersalesmanager.engine.api.dto.SalesDataRequestDTO;
import com.tigersalesmanager.engine.api.dto.SalesDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesDataMapper {

    private final BusinessMapper businessMapper;

    public SalesDataDomain toDomain(SalesData entity) {
        return SalesDataDomain.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .month(entity.getMonth())
                .grossSales(entity.getGrossSales())
                .taxableSales(entity.getTaxableSales())
                .business(businessMapper.toDomain(entity.getBusiness()))
                .build();
    }

    public SalesData toEntity(SalesDataDomain domain) {
        return SalesData.builder()
                .id(domain.getId())
                .year(domain.getYear())
                .month(domain.getMonth())
                .grossSales(domain.getGrossSales())
                .taxableSales(domain.getTaxableSales())
                .business(businessMapper.toEntity(domain.getBusiness()))
                .build();
    }

    public SalesDataDomain toDomain(SalesDataRequestDTO dto) {
        return SalesDataDomain.builder()
                .year(dto.getYear())
                .month(dto.getMonth())
                .grossSales(dto.getGrossSales())
                .taxableSales(dto.getTaxableSales())
                .business(BusinessDomain.builder().id(dto.getBusinessId()).build())
                .build();
    }

    public SalesDataResponseDTO toResponseDTO(SalesDataDomain domain) {
        return SalesDataResponseDTO.builder()
                .id(domain.getId())
                .businessId(domain.getBusiness().getId())
                .year(domain.getYear())
                .month(domain.getMonth())
                .grossSales(domain.getGrossSales())
                .taxableSales(domain.getTaxableSales())
                .build();
    }
}
