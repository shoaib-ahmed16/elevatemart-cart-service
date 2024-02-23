package com.elevatemartcartservice.domain;

import com.elevatemartcartservice.dto.enums.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<CartProduct> products;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    private double taxAmount;
    private double subTotal;
    private double total;
    private int totalQuantity;
    private double discountAmount;
    private double subTotalAfterDiscount;
    private  double totalAfterDiscount;
    private double shippingAmount;
    private String countryCode;
    private CartStatus status;

    public void addProduct(CartProduct product){
        if(Objects.isNull(this.products)){
            this.products =new ArrayList<>();
        }
        this.products.add(product);
        product.setCart(this);
    }
    public void removeProduct(CartProduct product){
        this.products =this.products.stream()
                .filter(prod -> !prod.equals(product))
                .collect(Collectors.toList());

    }
}
