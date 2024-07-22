package com.example.orderUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cook")
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cook_id")
    private Long cookId;

    @Column(name = "name")
    private String name;




}
