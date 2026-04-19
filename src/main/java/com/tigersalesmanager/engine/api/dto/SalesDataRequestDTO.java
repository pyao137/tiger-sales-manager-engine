package com.tigersalesmanager.engine.api.dto;

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
public class SalesDataRequestDTO {
    private UUID businessId;
    private Integer year;
    private Integer month;
    private BigDecimal grossSales;
    private BigDecimal taxableSales;
}
