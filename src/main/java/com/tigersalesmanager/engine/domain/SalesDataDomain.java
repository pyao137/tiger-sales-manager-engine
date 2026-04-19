package com.tigersalesmanager.engine.domain;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDataDomain {
    private UUID id;
    private Integer year;
    private Integer month;
    private BigDecimal grossSales;
    private BigDecimal taxableSales;
    private BusinessDomain business;
}
