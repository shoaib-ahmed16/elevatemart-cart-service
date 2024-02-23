package com.elevatemartcartservice.repository;

import com.elevatemartcartservice.domain.Cart;
import com.elevatemartcartservice.dto.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUsernameAndStatusIn(String username, List<CartStatus> status);
    Optional<Cart> findByUsernameAndStatus(String username, CartStatus status);

}
