package com.elevatemartcartservice.service;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;

public interface MicroServicesCallRouter {
    Product foundProduct(String sku) throws ProductServiceException, ProductServiceSkuProductNotFound;
}
