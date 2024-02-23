package com.elevatemartcartservice.service;

import com.elevatemartcartservice.domain.CartProduct;

public interface CartService {
    String addToCart(CartProduct cartProductDto) throws InterruptedException;
    String removeFromCart(CartProduct cartProductDto);
    String updateToCart(CartProduct cartProductDto);
}
