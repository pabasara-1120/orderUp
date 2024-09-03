package com.example.orderUp.dto;

import com.example.orderUp.entity.Order;
import com.example.orderUp.entity.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class OrderDTO {
    private Long orderId;
    private LocalDateTime orderTime;
    private OrderStatus status;
    private Long tableId;
    private Long waiterId;
    private LocalDateTime finishedTime;

}
