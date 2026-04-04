package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.dto.CustomerDTO;
import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import com.shankar.intelligentrestaurantmanagementsystem.entity.KotItem;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Order;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.OrderMapper;
import lombok.Getter;

import java.util.List;


public class ProcessOrder {
    @Getter
    private final CustomerDTO customerDto;

    @Getter
    private final Order order;

    private final OrderMapper orderMapper;

    public ProcessOrder(OrderRequest orderRequest, OrderMapper orderMapper) {
        this.customerDto = orderRequest.getCustomer();
        this.orderMapper = orderMapper;
        this.order = orderMapper.toEntity(orderRequest);
    }

    public Kot generateKot() {

        // while saving kot need reference of order
        return Kot.builder()
                .status(KotStatus.QUEUED)
                .deviceId("")
                .build();
    }


}
