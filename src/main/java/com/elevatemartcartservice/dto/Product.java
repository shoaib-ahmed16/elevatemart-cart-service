package com.elevatemartcartservice.dto;

import com.elevatemartcartservice.dto.convertor.TaxConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String sku;
    private String name;
    private String type;
    private Boolean isTaxable;
    private Double price;
    private String imageUrl;
    @ElementCollection
    @Convert(converter = TaxConverter.class)
    private List<Tax> taxes;
}