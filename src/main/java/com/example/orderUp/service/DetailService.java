package com.example.orderUp.service;

import com.example.orderUp.dto.ItemDTO;
import com.example.orderUp.dto.OrderDetailsDTO;

import java.util.List;

public interface DetailService {
    OrderDetailsDTO addItemToDetails(Long orderDetailsId, ItemDTO itemDTO);
    List<OrderDetailsDTO> getOrderDetailsByCustomer(Long customerId);
    OrderDetailsDTO addOrderDetailsfromCustomer(Long customerId, OrderDetailsDTO orderDetailsDTO);
    List<OrderDetailsDTO> addOrderDetailsfromCart(Long cartId);

}
