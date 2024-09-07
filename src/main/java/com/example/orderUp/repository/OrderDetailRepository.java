package com.example.orderUp.repository;

import com.example.orderUp.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {
    List<OrderDetails> findByCustomer_CustomerId(Long customerId);


}
