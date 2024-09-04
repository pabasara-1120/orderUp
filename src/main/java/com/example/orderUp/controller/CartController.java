package com.example.orderUp.controller;

import com.example.orderUp.dto.CartDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.entity.Cart;
import com.example.orderUp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customer_id}")
    public ResponseEntity<CartDTO> getCartByCustomer(@PathVariable Long customer_id){
        CartDTO cartDTO = cartService.getCartByCustomerId(customer_id);
        if(cartDTO!=null){
            return ResponseEntity.ok(cartDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{customer_id}")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long customer_id){
        CartDTO cartDTO = cartService.clearCart(customer_id);
        if(cartDTO!=null){
            return ResponseEntity.ok(cartDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{customerId}/add-item")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long customerId, @RequestBody OrderDetailsDTO orderDetailsDTO) {
        CartDTO updatedCart = cartService.addItemToCart(customerId, orderDetailsDTO);
        return ResponseEntity.ok(updatedCart);
    }




}
