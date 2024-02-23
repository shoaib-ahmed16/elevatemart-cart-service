package com.elevatemartcartservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String couponName;
    private double discountPercentage;
    private double minimumCartTotalAmount;

    @OneToMany(mappedBy = "coupon")
    @JsonIgnore
    private List<Cart> carts;


}
