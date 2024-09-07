package com.example.orderUp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "order_item_ingredients", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;


    @JsonIgnore
    @ManyToMany(mappedBy = "orderItems")
    private List<Cook> cooks;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderItems")
    private List<OrderDetails> orderDetails;



}
