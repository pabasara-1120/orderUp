package com.example.orderUp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderDetailsDTO {

    private Long orderDetailsId;
    private Long orderId;
    private Long customerId;
    private int quantity;
    private List<ItemDTO> itemDTOList;

}
