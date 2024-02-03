package com.elevatemartcartservice.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    private Long taxId;
    private String type;
    private  String code;
    private String displayName;
    private Double percent;
}
