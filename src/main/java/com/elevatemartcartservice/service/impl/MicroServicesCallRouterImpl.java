package com.elevatemartcartservice.service.impl;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.dto.enums.CallMicroservice;
import com.elevatemartcartservice.exception.ProductServiceException;
import com.elevatemartcartservice.service.MicroServicesCallRouter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Service
public class MicroServicesCallRouterImpl implements MicroServicesCallRouter {

    private final WebClient webClient;

    @Autowired
    public MicroServicesCallRouterImpl(WebClient.Builder webClientBuilder) {
        this.webClient =webClientBuilder.build();;
    }

    @Override
    public Product foundProduct(String sku)throws ProductServiceException {
        ResponseEntity<Product> response;
        try {
            log.info("Request Sent to Product service for product details retrieval. with Sku :{}",sku);
            response = webClient.get()
                    .uri(URI.create(CallMicroservice.PRODUCT.getUrl() + sku))
                    .retrieve()
                    .toEntity(Product.class)
                    .block();
        }catch (Exception exc){
            throw new ProductServiceException(exc.getMessage());
        }
        if(response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody())){
            return response.getBody();
        }
        else if(response.getStatusCode().is5xxServerError()){
            throw new ProductServiceException("Product service is not available!!!");
        }else {
            return new Product();
        }
    }

}
