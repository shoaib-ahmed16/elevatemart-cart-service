package com.elevatemartcartservice.utils;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.service.MicroServicesCallRouter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class ProductFetch implements Callable<Product> {

    private  String sku;
    private MicroServicesCallRouter callRouter;
    @Override
    public Product call() throws Exception {
        log.info("Initializing request to Product service for product details retrieval.");
        return callRouter.foundProduct(sku);
    }
    public ProductFetch(String sku, MicroServicesCallRouter callRouter){
        this.callRouter=callRouter;
        this.sku=sku;
    }
}
