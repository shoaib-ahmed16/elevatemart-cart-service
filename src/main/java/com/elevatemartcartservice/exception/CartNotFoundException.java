package com.elevatemartcartservice.exception;

@NoArgsConstructor
public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message){
        super(message);
    }
}
