package com.elevatemartcartservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtInValidToken extends RuntimeException {
    public JwtInValidToken(String message){
        super(message);
    }
}
