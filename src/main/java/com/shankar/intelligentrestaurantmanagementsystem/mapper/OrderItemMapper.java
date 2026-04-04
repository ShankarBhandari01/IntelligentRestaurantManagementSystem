package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderItemsDTO;
import com.shankar.intelligentrestaurantmanagementsystem.entity.OrderItems;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper implements Mapper<OrderItems, OrderItemsDTO, OrderItemsDTO> {
    private final MenuItemMapper menuItemMapper;

    @Override
    public OrderItems toEntity(@NonNull OrderItemsDTO orderItemsDTO) {
        return OrderItems.builder()
                .quantity(orderItemsDTO.getQuantity())
                .totalPrice(orderItemsDTO.getTotalPrice())
                .special_requests(orderItemsDTO.getSpecial_requests())
                .status(orderItemsDTO.getStatus())
                .build();
    }

    @Override
    public OrderItemsDTO toResponse(@NonNull OrderItems entity) {
        return OrderItemsDTO.builder()
                .id(entity.getId())
                //.menuItems(menuItemMapper.toResponse(entity.getMenuItem()))
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .special_requests(entity.getSpecial_requests())
                .status(entity.getStatus())
                .build();
    }
}
