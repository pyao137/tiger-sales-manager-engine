package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.data.model.SalesData;
import com.tigersalesmanager.engine.data.repo.BusinessRepository;
import com.tigersalesmanager.engine.data.repo.SalesDataRepository;
import com.tigersalesmanager.engine.api.dto.SalesDataRequestDTO;
import com.tigersalesmanager.engine.api.dto.SalesDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesDataService {
    private final SalesDataRepository salesDataRepository;
    private final BusinessRepository businessRepository;

    public SalesDataResponseDTO inputSalesData(SalesDataRequestDTO dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        SalesData salesData = salesDataRepository.findByBusinessAndYearAndMonth(business, dto.getYear(), dto.getMonth())
                .orElse(new SalesData());

        salesData.setBusiness(business);
        salesData.setYear(dto.getYear());
        salesData.setMonth(dto.getMonth());
        salesData.setGrossSales(dto.getGrossSales());
        salesData.setTaxableSales(dto.getTaxableSales());

        salesData = salesDataRepository.save(salesData);
        return mapToDTO(salesData);
    }

    public SalesDataResponseDTO getSalesData(UUID businessId, Integer year, Integer month) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        
        SalesData salesData = salesDataRepository.findByBusinessAndYearAndMonth(business, year, month)
                .orElseThrow(() -> new RuntimeException("Sales data not found"));
        
        return mapToDTO(salesData);
    }

    public List<SalesDataResponseDTO> listSalesData(UUID businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        
        return salesDataRepository.findByBusiness(business).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTaxes(UUID businessId, Integer year, Integer month) {
        SalesDataResponseDTO data = getSalesData(businessId, year, month);
        // Simplified tax calculation: 7% of taxable sales
        return data.getTaxableSales().multiply(new BigDecimal("0.07"));
    }

    private SalesDataResponseDTO mapToDTO(SalesData salesData) {
        return SalesDataResponseDTO.builder()
                .id(salesData.getId())
                .businessId(salesData.getBusiness().getId())
                .year(salesData.getYear())
                .month(salesData.getMonth())
                .grossSales(salesData.getGrossSales())
                .taxableSales(salesData.getTaxableSales())
                .build();
    }
}
