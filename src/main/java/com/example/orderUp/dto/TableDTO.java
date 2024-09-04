package com.example.orderUp.dto;

import com.example.orderUp.entity.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TableDTO {
    private Long tableId;
    private String url;
    private Long waiterId;

}
