package com.elevatemartcartservice.controller;

import com.elevatemartcartservice.dto.CartProductDto;
import com.elevatemartcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<String> addProductToCart(@RequestBody CartProductDto cartProductDto){
        return new ResponseEntity<>(cartService.addToCart(cartProductDto), HttpStatus.OK);
    }
}
