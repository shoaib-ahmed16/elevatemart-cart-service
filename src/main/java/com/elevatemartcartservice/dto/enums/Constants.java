package com.elevatemartcartservice.dto.enums;

public enum Constants {
    PERCENTAGE_DIVISOR(100),
    MIN_SIZE_CART_PRODUCT_LIST(1),
    COUNTRY("IN");
    private String str;
    private  int value;
    public String getStr(){
        return  this.str;
    }
    public int getValue(){
        return this.value;
    }
    Constants(int value){
        this.value=value;
    }
    Constants(String str){
        this.str=str;
    }
}
