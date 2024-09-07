package com.example.orderUp.service.serviceImpl;

import com.example.orderUp.dto.ItemDTO;
import com.example.orderUp.dto.OrderDetailsDTO;
import com.example.orderUp.entity.Customer;
import com.example.orderUp.entity.OrderDetails;
import com.example.orderUp.entity.OrderItem;
import com.example.orderUp.repository.CustomerRepository;
import com.example.orderUp.repository.OrderDetailRepository;
import com.example.orderUp.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsServiceImpl implements DetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public OrderDetailsDTO addItemToDetails(Long orderDetailsId, ItemDTO itemDTO) {
        Optional<OrderDetails> optionalOrderDetails = orderDetailRepository.findById(orderDetailsId);
        if (optionalOrderDetails.isPresent()){
            OrderDetails orderDetails = optionalOrderDetails.get();
            OrderItem orderItem = OrderItem.builder()
                            .itemId(itemDTO.getItemId())
                            .name(itemDTO.getName())
                                    .ingredients(itemDTO.getIngredients())
                                            .build();
            orderDetails.getOrderItems().add(orderItem);
            OrderDetails savedOrderDetail = orderDetailRepository.save(orderDetails);
            List<OrderItem> savedOrderDetailOrderItems = savedOrderDetail.getOrderItems();
            List<ItemDTO> itemDTOList = savedOrderDetailOrderItems.stream()
                    .map(item ->ItemDTO.builder()
                            .itemId(item.getItemId())
                            .name(item.getName())
                            .ingredients(item.getIngredients())
                            .build()).toList();

            return OrderDetailsDTO.builder()
                    .orderDetailsId(orderDetails.getOrderDetail_id())
                    .orderId(orderDetails.getOrder()!=null? orderDetails.getOrder().getOrderId():null)
                    .quantity(orderDetails.getQuantity())
                    .itemDTOList(itemDTOList)
                    .customerId(orderDetails.getCustomer()!=null? orderDetails.getCustomer().getCustomerId():null)
                    .build();
        }





        return null;
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
