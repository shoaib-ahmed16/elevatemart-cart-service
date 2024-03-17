package com.elevatemartcartservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenExpired extends RuntimeException{
    public TokenExpired(String message){
        super(message);
    }
}