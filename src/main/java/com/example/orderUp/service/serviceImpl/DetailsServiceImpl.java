package com.example.orderUp.service.serviceImpl;

import com.example.orderUp.dto.ItemDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.entity.Customer;
import com.example.orderUp.entity.OrderDetails;
import com.example.orderUp.entity.OrderItem;
import com.example.orderUp.repository.CustomerRepository;
import com.example.orderUp.repository.ItemRepository;
import com.example.orderUp.repository.OrderDetailRepository;
import com.example.orderUp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetailsServiceImpl implements DetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository orderItemRepository;

    @Override
    public OrderDetailsDTO addItemToDetails(Long orderDetailsId, ItemDTO itemDTO) {
        Optional<OrderDetails> optionalOrderDetails = orderDetailRepository.findById(orderDetailsId);
        if (optionalOrderDetails.isPresent()) {
            OrderDetails orderDetails = optionalOrderDetails.get();

            // Find or create the OrderItem
            OrderItem orderItem = orderItemRepository.findById(itemDTO.getItemId())
                    .orElse(OrderItem.builder()
                            .name(itemDTO.getName())
                            .ingredients(itemDTO.getIngredients())
                            .build());

            // Add the OrderItem to the OrderDetails' orderItemList
            orderDetails.getOrderItems().add(orderItem);

            // Save the updated OrderDetails (this will automatically update the join table)
            OrderDetails savedOrderDetails = orderDetailRepository.save(orderDetails);

            // Convert OrderItem list to ItemDTO list
            List<ItemDTO> itemDTOList = savedOrderDetails.getOrderItems().stream()
                    .map(item -> ItemDTO.builder()
                            .itemId(item.getItemId())
                            .name(item.getName())
                            .ingredients(item.getIngredients())
                            .build())
                    .collect(Collectors.toList());

            // Build and return the OrderDetailsDTO
            return OrderDetailsDTO.builder()
                    .orderDetailsId(savedOrderDetails.getOrderDetail_id())
                    .orderId(savedOrderDetails.getOrder() != null ? savedOrderDetails.getOrder().getOrderId() : null)
                    .quantity(savedOrderDetails.getQuantity())
                    .itemDTOList(itemDTOList)
                    .build();
        } else {
            throw new RuntimeException("OrderDetails with ID " + orderDetailsId + " not found.");
        }
    }


    @Override
    public List<OrderDetailsDTO> getOrderDetailsByCustomer(Long customerId) {
        List<OrderDetails> orderDetailsDTOS = orderDetailRepository.findByCustomer_CustomerId(customerId);
        if(orderDetailsDTOS!=null){

            return orderDetailsDTOS.stream()
                    .map(orderDetails -> OrderDetailsDTO.builder()
                            .orderDetailsId(orderDetails.getOrderDetail_id())
                            .customerId(orderDetails.getCustomer()!=null? orderDetails.getCustomer().getCustomerId():null)
                            .orderId(orderDetails.getOrder()!=null? orderDetails.getOrder().getOrderId():null)

                            .itemDTOList(orderDetails.getOrderItems().stream().map(item -> ItemDTO.builder()
                                    .itemId(item.getItemId())
                                    .name(item.getName())
                                    .ingredients(item.getIngredients())
                                    .build()).toList())
                            .quantity(orderDetails.getQuantity()).build()).toList();
        }
        return null;
        


    }

}
