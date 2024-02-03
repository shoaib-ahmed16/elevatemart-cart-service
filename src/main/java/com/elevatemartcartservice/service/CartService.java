package com.elevatemartcartservice.service;

import com.elevatemartcartservice.dto.CartProductDto;

public interface CartService {
     String addToCart(CartProductDto cartProductDto);
    String removeFromCart(CartProductDto cartProductDto);
    String updateToCart(CartProductDto cartProductDto);
}
