package com.elevatemartcartservice.dto.enums;

public enum CallMicroservice {

    PRODUCT("http://localhost:8800/product-service-elevatemart/api/v1/product/"),
    SECURITY("http://localhost:8800/product-service-elevatemart/");
    private final String url;
    public String getUrl(){
        return  this.url;
    }
    CallMicroservice(String url){
        this.url=url;
    }
}
