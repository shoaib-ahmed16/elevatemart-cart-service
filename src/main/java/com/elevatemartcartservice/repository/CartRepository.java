package com.elevatemartcartservice.repository;

import com.elevatemartcartservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
//    Optional<Cart> findByUsernameAndStatus(String username, String status);
}
