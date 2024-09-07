package com.example.orderUp.controller;

import com.example.orderUp.dto.ItemDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class OrderDetailsController {

    @Autowired
    private DetailService detailService;

    @PostMapping("/{orderDetailsId}/add-item")
    public ResponseEntity<OrderDetailsDTO> addItemToOrderDetails(
            @PathVariable Long orderDetailsId,
            @RequestBody ItemDTO itemDTO) {


        OrderDetailsDTO updatedOrderDetails = detailService.addItemToDetails(orderDetailsId, itemDTO);
        if(updatedOrderDetails!=null)
            return ResponseEntity.ok(updatedOrderDetails);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetailsByCustomer(@PathVariable Long customer_id){
        List<OrderDetailsDTO> orderDetailsDTOS = detailService.getOrderDetailsByCustomer(customer_id);
        if (orderDetailsDTOS!=null){
            return ResponseEntity.ok(orderDetailsDTOS);
        }
        return ResponseEntity.notFound().build();
    }
}
