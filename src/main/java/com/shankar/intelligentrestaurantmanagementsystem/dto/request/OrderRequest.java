package com.shankar.intelligentrestaurantmanagementsystem.dto.request;


import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderType;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderItemsDTO;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import com.shankar.intelligentrestaurantmanagementsystem.entity.OrderItems;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderRequest {
    private CustomerDTO customer;
    private String orderRemarks;
    private OrderType orderType;
    private List<OrderItemsDTO> items;
    private BigDecimal totalAmount;
    private OrderStatus status;

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", orderRemarks='" + orderRemarks + '\'' +
                ", orderType='" + orderType + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
