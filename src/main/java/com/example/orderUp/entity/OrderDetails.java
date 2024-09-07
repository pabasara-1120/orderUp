package com.example.orderUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetails_id")
    private Long orderDetail_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "quantity")
    private int quantity;

    @ManyToMany
    @JoinTable(
            name = "detail_item",
            joinColumns = @JoinColumn(name = "orderDetails_id"),
            inverseJoinColumns = @JoinColumn(name = "orderitem_id")
    )
    private List<OrderItem> orderItems = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;






}
