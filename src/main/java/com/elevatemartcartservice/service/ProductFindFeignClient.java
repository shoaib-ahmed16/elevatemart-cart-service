package com.elevatemartcartservice.service;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import com.elevatemartcartservice.service.failoverService.ProductFindFallBackFeignClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-micro-service",fallback = ProductFindFallBackFeignClientImpl.class)
public interface ProductFindFeignClient {

    @GetMapping("/api/v1/product/{sku}")
    Product foundProduct(@PathVariable String sku) throws ProductServiceException, ProductServiceSkuProductNotFound;
}
