package com.shankar.intelligentrestaurantmanagementsystem.dto.request;


import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import com.shankar.intelligentrestaurantmanagementsystem.entity.OrderItems;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequest {
    private CustomerDTO customer;
    private String orderRemarks;
    private String orderType;
    private List<OrderItems> items;
    private double totalAmount;
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
