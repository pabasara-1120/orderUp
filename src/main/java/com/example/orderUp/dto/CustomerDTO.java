package com.example.orderUp.dto;

import com.example.orderUp.entity.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Long tableId;
}
