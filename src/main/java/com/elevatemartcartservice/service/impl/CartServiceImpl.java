package com.elevatemartcartservice.service.impl;

import com.elevatemartcartservice.dto.CartProductDto;
import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.dto.enums.CallMicroservice;
import com.elevatemartcartservice.repository.CartRepository;
import com.elevatemartcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
public class CartServiceImpl implements CartService {

    private final WebClient webClient;
    @Autowired
    public CartServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Autowired
    private CartRepository cartRepo;

    @Override
    public String addToCart(CartProductDto cartProductDto) {
        ResponseEntity<Product>  prodResponse =webClient.get()
                .uri(URI.create(CallMicroservice.PRODUCT.getUrl()+cartProductDto.getProductSku()))
                .retrieve()
                .toEntity(Product.class)
                .block();
        Product product = prodResponse.getBody();
        System.out.println(product);
        return "Product is Add successfully.";
    }

    @Override
    public String removeFromCart(CartProductDto cartProductDto) {
        return null;
    }

    @Override
    public String updateToCart(CartProductDto cartProductDto) {
        return null;
    }
}
