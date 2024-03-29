package com.elevatemartcartservice.domain;

import com.elevatemartcartservice.dto.Product;
import com.elevatemartcartservice.dto.convertor.ProductConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    private String username;

    @ElementCollection
    @Convert(converter = ProductConverter.class)
    private List<Product> productList;
    private String discountCoupon;

    private double taxAmount;
    private double subTotal;
    private double total;
    private double discountAmount;
    private double subTotalAfterDiscount;
    private  double totalAfterDiscount;
    private double shippingAmount;
    private String countryCode;
    private String status;

}
