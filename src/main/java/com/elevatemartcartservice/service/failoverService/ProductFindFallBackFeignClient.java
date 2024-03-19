package com.elevatemartcartservice.service.failoverService;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "product-failOver-micro-service")
public interface ProductFindFallBackFeignClient {
    @GetMapping("/product-failOver-micro-service")
    Product foundProduct(@PathVariable String sku) throws ProductServiceException, ProductServiceSkuProductNotFound;

}
