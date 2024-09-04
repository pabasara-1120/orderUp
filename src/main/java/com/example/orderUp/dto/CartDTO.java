package com.example.orderUp.dto;

import com.example.orderUp.entity.OrderDetails;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CartDTO {
    private Long cartId;
    private int quantity;
    private LocalDateTime time;
    private List<OrderDetailsDTO> orderDetails;
}