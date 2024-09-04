package com.example.orderUp.service;

import com.example.orderUp.dto.CartDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.entity.Cart;

public interface CartService {
    CartDTO addItemToCart(Long customerId, OrderDetailsDTO orderDetailsDTO);
    CartDTO getCartByCustomerId(Long customerId);
    CartDTO clearCart(Long customerId);


}
