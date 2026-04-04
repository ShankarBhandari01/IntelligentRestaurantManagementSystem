package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class OrderFilterRequest {

    private OrderStatus status;
    private Long tableId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
