package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderItemStatus;

import com.shankar.intelligentrestaurantmanagementsystem.entity.MenuItems;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemsDTO {
    private String id;
    private String menuItemId;
    private int quantity;
    private double pricePerItem;
    private BigDecimal totalPrice;
    private String special_requests;
    private OrderItemStatus status;
}
