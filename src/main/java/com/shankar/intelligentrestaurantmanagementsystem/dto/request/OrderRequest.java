package com.shankar.intelligentrestaurantmanagementsystem.dto.request;


import com.shankar.intelligentrestaurantmanagementsystem.entity.Customer;
import com.shankar.intelligentrestaurantmanagementsystem.entity.OrderItems;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequest {
    private Customer customer;
    private String orderRemarks;
    private String orderType;
    private List<OrderItems> items;
    private double totalAmount;

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
