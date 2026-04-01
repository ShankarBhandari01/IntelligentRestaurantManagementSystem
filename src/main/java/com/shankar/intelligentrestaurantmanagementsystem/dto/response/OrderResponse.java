package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private CustomerDTO customer;
    private String orderRemarks;
    private String orderType;
    private List<OrderItems> items;
    private double totalAmount;
    private OrderStatus status;
    private Kot kot;
}
