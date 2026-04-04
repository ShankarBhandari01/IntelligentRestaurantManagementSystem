package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderType;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private CustomerDTO customer;
    private String orderRemarks;
    private OrderType orderType;
    private Set<OrderItemsDTO> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<Kot> kots;
}
