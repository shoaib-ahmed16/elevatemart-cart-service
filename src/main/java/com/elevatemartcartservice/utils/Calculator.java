package com.elevatemartcartservice.utils;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.dto.enums.Constants;
import org.springframework.stereotype.Component;

@Component
public class Calculator {
    public Double add(Double amount1, Double amount2){
        return amount1 + amount2;
    }
    public Integer addInts(int val2, int val1){
        return val2 + val2;
    }
    public Double subtract(Double amount1, Double amount2){
        return amount1 - amount2;
    }
    public Integer subtractInts(Integer val1, Integer val2){
        return val1 - val2;
    }
    public Double doubleToIntMultiply(Double amount1, int qty){
        return amount1 * qty;
    }
    public double taxCalculation(Product product) {
        return product.getTaxes()
                .stream()
                .mapToDouble(tax -> tax.getPercent() / Constants.PERCENTAGE_DIVISOR.getValue() * product.getPrice())
                .sum();
    }
    public Double doubleToIntDivisor(Double amount1, int qty){
        return amount1 / qty;
    }
}
