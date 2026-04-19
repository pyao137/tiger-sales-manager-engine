package com.tigersalesmanager.engine.domain;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDomain {
    private UUID id;
    private String name;
    private String taxId;
    private UserDomain owner;
    private List<SalesDataDomain> salesData;
}
