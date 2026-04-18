package com.tigersalesmanager.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesDataResponseDTO {
    private UUID id;
    private UUID businessId;
    private Integer year;
    private Integer month;
    private BigDecimal grossSales;
    private BigDecimal taxableSales;
}
