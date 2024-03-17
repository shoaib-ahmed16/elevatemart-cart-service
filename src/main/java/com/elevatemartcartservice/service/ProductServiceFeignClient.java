package com.elevatemartcartservice.service;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-micro-service")
public interface ProductServiceFeignClient {

    @GetMapping("/api/v1/product/{sku}")
    ResponseEntity<Product> foundProduct(@PathVariable String sku) throws ProductServiceException, ProductServiceSkuProductNotFound;
}
