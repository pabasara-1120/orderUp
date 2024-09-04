package com.example.orderUp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "url", unique = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "waiter_id",nullable = true)
    private Waiter waiter;

    @JsonIgnore
    @OneToOne(mappedBy = "restaurantTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order orders;






}
