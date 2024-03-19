package com.elevatemartcartservice.service.failoverService;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.exception.ProductServiceSkuProductNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductFindFallBackFeignClientImpl implements ProductFindFallBackFeignClient {

    private final ProductFindFallBackFeignClient failOverServiceFeignClient;
    @Override
    public Product foundProduct(String sku) throws ProductServiceException, ProductServiceSkuProductNotFound {
        return failOverServiceFeignClient.foundProduct(sku);
    }
}
