package com.elevatemartcartservice.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String sku;
    private String name;
    private String type;
    private Boolean isTaxable;
    private Double price;
    private String imageUrl;
    @Basic
    @ElementCollection
    private List<Tax> taxes;
}