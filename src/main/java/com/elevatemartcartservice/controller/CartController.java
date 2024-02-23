package com.elevatemartcartservice.controller;

import com.elevatemartcartservice.domain.CartProduct;
import com.elevatemartcartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<String> addProductToCart(@RequestBody CartProduct cartProductDto) throws InterruptedException {
        return new ResponseEntity<>(cartService.addToCart(cartProductDto), HttpStatus.OK);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<String> removeProductFromCart(@RequestBody CartProduct cartProductDto) throws InterruptedException {
        return new ResponseEntity<>(cartService.removeFromCart(cartProductDto), HttpStatus.OK);
    }
    @PutMapping("/updateTheCart")
    public ResponseEntity<String> updateTheCartProduct(@RequestBody CartProduct cartProductDto) throws InterruptedException {
        return new ResponseEntity<>(cartService.updateToCart(cartProductDto), HttpStatus.OK);
    }
}
