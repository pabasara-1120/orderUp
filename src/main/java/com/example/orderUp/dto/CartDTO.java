package com.example.orderUp.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CartDTO {

    private Long cart_id;
    private int quantity;
    private LocalDateTime time;
}
