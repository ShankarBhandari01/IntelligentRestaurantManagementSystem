package com.shankar.intelligentrestaurantmanagementsystem.Integration;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final OrderService orderService;

    public OrderConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    //@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(OrderRequest orderRequest) {
        orderService.processOrder(orderRequest);
    }
}
