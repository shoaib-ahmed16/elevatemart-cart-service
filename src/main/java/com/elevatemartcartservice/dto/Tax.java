package com.elevatemartcartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tax {
    private Long taxId;
    private String type;
    private  String code;
    private String displayName;
    private Double percent;
}
