package com.example.orderUp.repository;

import com.example.orderUp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomer_CustomerId(Long customerId);

}
