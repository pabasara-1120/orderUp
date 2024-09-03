package com.example.orderUp.service.serviceImpl;


import com.example.orderUp.dto.OrderDTO;
import com.example.orderUp.entity.Order;
import com.example.orderUp.entity.OrderStatus;
import com.example.orderUp.entity.RestaurantTable;
import com.example.orderUp.entity.Waiter;
import com.example.orderUp.repository.OrderRepository;
import com.example.orderUp.repository.TableRepository;
import com.example.orderUp.repository.WaiterRepository;
import com.example.orderUp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private WaiterRepository waiterRepository;


    @Override
    public List<OrderDTO> getALlOrders() {
        return orderRepository.findAll().stream()
                .map(order -> OrderDTO.builder()
                        .orderId(order.getOrderId())
                        .orderTime(order.getOrderTime())
                        .finishedTime(order.getFinishedTime())
                        .status(order.getStatus())
                        .tableId(order.getRestaurantTable()!=null? order.getRestaurantTable().getTableId(): null)
                        .waiterId(order.getWaiter()!=null? order.getWaiter().getWaiterId():null)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            return OrderDTO.builder()
                    .orderId(order.getOrderId())
                    .orderTime(order.getOrderTime())
                    .finishedTime(order.getFinishedTime())
                    .status(order.getStatus())
                    .tableId(order.getRestaurantTable()!=null? order.getRestaurantTable().getTableId():null)
                    .waiterId(order.getWaiter()!=null? order.getWaiter().getWaiterId():null)
                    .build();
        }
        return null;
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) { //make the default pending
        RestaurantTable restaurantTable = orderDTO.getTableId()!=null? tableRepository.findById(orderDTO.getTableId()).orElse(null) :null;
        Waiter waiter = orderDTO.getWaiterId()!=null? waiterRepository.findById(orderDTO.getWaiterId()).orElse(null):null;
        Order order = Order.builder()
                .orderTime(orderDTO.getOrderTime())
                .finishedTime(orderDTO.getFinishedTime())
                .status(orderDTO.getStatus())
                .restaurantTable(restaurantTable)
                .waiter(waiter)
                .build();


        Order savedOrder = orderRepository.save(order);

        return OrderDTO.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .finishedTime(savedOrder.getFinishedTime())
                .status(savedOrder.getStatus())
                .tableId(savedOrder.getRestaurantTable()!=null? savedOrder.getRestaurantTable().getTableId():null )
                .waiterId(savedOrder.getWaiter()!=null?savedOrder.getWaiter().getWaiterId():null)
                .build();
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderTime(orderDTO.getOrderTime());
            order.setFinishedTime(orderDTO.getFinishedTime());
            order.setStatus(orderDTO.getStatus());
            order.setRestaurantTable(orderDTO.getTableId()!=null? tableRepository.findById(orderDTO.getTableId()).orElse(null):null);
            order.setWaiter(orderDTO.getWaiterId()!=null? waiterRepository.findById(orderDTO.getWaiterId()).orElse(null):null);

            Order updatedOrder = orderRepository.save(order);

            return OrderDTO.builder()
                    .orderId(updatedOrder.getOrderId())
                    .orderTime(updatedOrder.getOrderTime())
                    .finishedTime(updatedOrder.getFinishedTime())
                    .status(updatedOrder.getStatus())
                    .tableId(updatedOrder.getRestaurantTable() != null ? updatedOrder.getRestaurantTable().getTableId() : null)
                    .waiterId(updatedOrder.getWaiter()!=null?updatedOrder.getWaiter().getWaiterId():null)

                    .build();
        }
        return null;

    }

    @Override
    public boolean deleteOrder(Long id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public OrderDTO confirmOrder(Long orderId, Long waiterId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Waiter> optionalWaiter = waiterRepository.findById(waiterId);

        if(optionalOrder.isPresent() && optionalWaiter.isPresent()){
            Order order = optionalOrder.get();
            Waiter waiter = optionalWaiter.get();

            if(order.getStatus()== OrderStatus.PENDING){
                order.setStatus(OrderStatus.CONFIRMED);
                order.setWaiter(waiter);

                Order confiremedOrder = orderRepository.save(order);

                return OrderDTO.builder()
                        .orderId(confiremedOrder.getOrderId())
                        .orderTime(confiremedOrder.getOrderTime())
                        .finishedTime(confiremedOrder.getFinishedTime())
                        .status(confiremedOrder.getStatus())
                        .tableId(confiremedOrder.getRestaurantTable() != null ? confiremedOrder.getRestaurantTable().getTableId() : null)
                        .waiterId(confiremedOrder.getWaiter()!=null?confiremedOrder.getWaiter().getWaiterId():null)

                        .build();

            }
        }
        return null;
    }
}
