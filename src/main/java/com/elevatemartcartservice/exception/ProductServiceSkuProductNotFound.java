package com.elevatemartcartservice.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductServiceSkuProductNotFound extends RuntimeException {
    public ProductServiceSkuProductNotFound(String message){
        super(message);
    }
}
