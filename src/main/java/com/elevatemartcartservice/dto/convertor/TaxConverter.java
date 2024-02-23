package com.elevatemartcartservice.dto.convertor;

import com.elevatemartcartservice.dto.Tax;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class TaxConverter implements AttributeConverter<List<Tax>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Tax> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Product list to JSON", e);
        }
    }

    @Override
    public List<Tax> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Tax>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Product list", e);
        }
    }
}