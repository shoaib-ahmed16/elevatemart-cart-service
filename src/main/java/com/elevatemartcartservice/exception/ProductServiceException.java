package com.elevatemartcartservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductServiceException extends RuntimeException{
    public ProductServiceException(String message){
        super(message);
    }
}
