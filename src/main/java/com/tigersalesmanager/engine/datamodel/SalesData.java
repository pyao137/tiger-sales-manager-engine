package com.tigersalesmanager.engine.datamodel;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "sales_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal grossSales;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal taxableSales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
}
