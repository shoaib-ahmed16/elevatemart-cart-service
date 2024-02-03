package com.elevatemartcartservice.dto.convertor;

import com.elevatemartcartservice.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class ProductConverter implements AttributeConverter<List<Product>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Product> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Product list to JSON", e);
        }
    }

    @Override
    public List<Product> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Product>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Product list", e);
        }
    }
}

