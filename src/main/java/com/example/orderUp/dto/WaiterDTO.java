package com.example.orderUp.dto;

import com.example.orderUp.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WaiterDTO {
    public Long waiterId;
    public String name;
    public String phoneNumber;
}
