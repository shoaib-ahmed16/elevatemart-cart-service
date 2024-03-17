package com.elevatemartcartservice.utils;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import com.elevatemartcartservice.service.ProductServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.concurrent.Callable;

@Slf4j
public class ProductFetch implements Callable<Product> {

    private  String sku;
    private ProductServiceFeignClient callRouter;
    @Override
    public Product call() throws Exception {
        log.info("Initializing request to Product service for product details retrieval.");
        ResponseEntity<Product> product= callRouter.foundProduct(sku);
        if(product.getStatusCode().is2xxSuccessful() && Objects.nonNull(product.getBody()))
         return product.getBody();
        else
            throw new ProductServiceSkuProductNotFound("No product Exist for this sku: "+sku);
    }
    public ProductFetch(String sku, ProductServiceFeignClient callRouter){
        this.callRouter=callRouter;
        this.sku=sku;
    }
}
