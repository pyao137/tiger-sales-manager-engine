package com.tigersalesmanager.engine.services;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.data.model.SalesData;
import com.tigersalesmanager.engine.data.repo.BusinessRepository;
import com.tigersalesmanager.engine.data.repo.SalesDataRepository;
import com.tigersalesmanager.engine.domain.SalesDataDomain;
import com.tigersalesmanager.engine.mappers.SalesDataMapper;
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
    private final SalesDataMapper salesDataMapper;

    public SalesDataDomain inputSalesData(SalesDataDomain salesDataDomain) {
        Business business = businessRepository.findById(salesDataDomain.getBusiness().getId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        SalesData salesData = salesDataRepository.findByBusinessAndYearAndMonth(
                        business, salesDataDomain.getYear(), salesDataDomain.getMonth())
                .orElse(new SalesData());

        salesData.setBusiness(business);
        salesData.setYear(salesDataDomain.getYear());
        salesData.setMonth(salesDataDomain.getMonth());
        salesData.setGrossSales(salesDataDomain.getGrossSales());
        salesData.setTaxableSales(salesDataDomain.getTaxableSales());

        salesData = salesDataRepository.save(salesData);
        return salesDataMapper.toDomain(salesData);
    }

    public SalesDataDomain getSalesData(UUID businessId, Integer year, Integer month) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        SalesData salesData = salesDataRepository.findByBusinessAndYearAndMonth(business, year, month)
                .orElseThrow(() -> new RuntimeException("Sales data not found"));

        return salesDataMapper.toDomain(salesData);
    }

    public List<SalesDataDomain> listSalesData(UUID businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        return salesDataRepository.findByBusiness(business).stream()
                .map(salesDataMapper::toDomain)
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTaxes(UUID businessId, Integer year, Integer month) {
        SalesDataDomain data = getSalesData(businessId, year, month);
        // Simplified tax calculation: 7% of taxable sales
        return data.getTaxableSales().multiply(new BigDecimal("0.07"));
    }
}
