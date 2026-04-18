package com.tigersalesmanager.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRequestDTO {
    private String name;
    private String taxId;
    private UUID ownerId;
}
