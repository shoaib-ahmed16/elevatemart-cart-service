package com.elevatemartcartservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CartAddUnknownServerError extends RuntimeException {
    public CartAddUnknownServerError(String message){
        super(message);
    }
}
