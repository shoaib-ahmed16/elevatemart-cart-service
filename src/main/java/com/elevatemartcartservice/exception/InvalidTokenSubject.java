package com.elevatemartcartservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTokenSubject extends RuntimeException{
    public InvalidTokenSubject(String message){
        super(message);
    }
}
