package com.tigersalesmanager.engine.api.controllers;

import com.tigersalesmanager.engine.api.dto.SalesDataRequestDTO;
import com.tigersalesmanager.engine.api.dto.SalesDataResponseDTO;
import com.tigersalesmanager.engine.domain.SalesDataDomain;
import com.tigersalesmanager.engine.mappers.SalesDataMapper;
import com.tigersalesmanager.engine.services.SalesDataService;
import com.tigersalesmanager.engine.services.TaxFilingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesDataController {
    private final SalesDataService salesDataService;
    private final TaxFilingService taxFilingService;
    private final SalesDataMapper salesDataMapper;

    @PostMapping
    public ResponseEntity<SalesDataResponseDTO> inputSalesData(@RequestBody SalesDataRequestDTO salesDataDTO) {
        SalesDataDomain domain = salesDataMapper.toDomain(salesDataDTO);
        SalesDataDomain created = salesDataService.inputSalesData(domain);
        return ResponseEntity.ok(salesDataMapper.toResponseDTO(created));
    }

    @GetMapping("/{businessId}/{year}/{month}")
    public ResponseEntity<SalesDataResponseDTO> getSalesData(
            @PathVariable UUID businessId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        SalesDataDomain domain = salesDataService.getSalesData(businessId, year, month);
        return ResponseEntity.ok(salesDataMapper.toResponseDTO(domain));
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<List<SalesDataResponseDTO>> listSalesData(@PathVariable UUID businessId) {
        List<SalesDataDomain> domains = salesDataService.listSalesData(businessId);
        return ResponseEntity.ok(domains.stream()
                .map(salesDataMapper::toResponseDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{businessId}/{year}/{month}/tax")
    public ResponseEntity<BigDecimal> calculateTaxes(
            @PathVariable UUID businessId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        return ResponseEntity.ok(salesDataService.calculateTaxes(businessId, year, month));
    }

    @PostMapping("/{businessId}/{year}/{month}/efile")
    public ResponseEntity<Boolean> eFileTaxes(
            @PathVariable UUID businessId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        BigDecimal taxAmount = salesDataService.calculateTaxes(businessId, year, month);
        return ResponseEntity.ok(taxFilingService.eFileTaxes(businessId, year, month, taxAmount));
    }
}
