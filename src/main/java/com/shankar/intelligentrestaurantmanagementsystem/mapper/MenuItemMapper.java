package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.MenuItemResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.MenuItems;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemMapper implements Mapper<MenuItems, MenuItemRequest, MenuItemResponse> {
    private final LanguageMapper languageMapper;

    @Override
    public MenuItems toEntity(@NonNull MenuItemRequest menuItemsRequest) {
        return MenuItems.builder()
                .itemName(languageMapper.toEntity(menuItemsRequest.getItemName()))
                .description(languageMapper.toEntity(menuItemsRequest.getDescription()))
                .remarks(languageMapper.toEntity(menuItemsRequest.getRemarks()))
                .quantity(menuItemsRequest.getQuantity())
                .vatPercent(menuItemsRequest.getVatPercent())
                .amount(menuItemsRequest.getAmount())
                .isSpicy(menuItemsRequest.getIsSpicy())
                .isGlutenFree(menuItemsRequest.getIsGlutenFree())
                .isVegan(menuItemsRequest.getIsVegan())
                .isActive(menuItemsRequest.getIsActive())
                .isDeleted(menuItemsRequest.getIsDeleted())
                .currency(menuItemsRequest.getCurrency())
                .isDayOfWeek(menuItemsRequest.getIsDayOfWeek())
                .dayOfWeek(menuItemsRequest.getDayOfWeek())
                .nameOfWeek(languageMapper.toEntity(menuItemsRequest.getNameOfWeek()))
                .spiceLevel(menuItemsRequest.getSpiceLevel())
                .isVegetarian(menuItemsRequest.getIsVegetarian())
                .isLactoseFree(menuItemsRequest.getIsLactoseFree())
                .image(menuItemsRequest.getImage()) // mode to es2
                .build();
    }

    @Override
    public MenuItemResponse toResponse(@NonNull MenuItems menuItems) {
        return MenuItemResponse.builder()
                .id(menuItems.getId())
                .itemName(languageMapper.toResponse(menuItems.getItemName()))
                .description(languageMapper.toResponse(menuItems.getDescription()))
                .remarks(languageMapper.toResponse(menuItems.getRemarks()))
                .quantity(menuItems.getQuantity()).vatPercent(menuItems.getVatPercent())
                .amount(menuItems.getAmount()).isSpicy(menuItems.getIsSpicy())
                .isGlutenFree(menuItems.getIsGlutenFree())
                .isVegan(menuItems.getIsVegan())
                .isActive(menuItems.getIsActive())
                .isDeleted(menuItems.getIsDeleted())
                .currency(menuItems.getCurrency())
                .isDayOfWeek(menuItems.getIsDayOfWeek())
                .dayOfWeek(menuItems.getDayOfWeek())
                .nameOfWeek(languageMapper.toResponse(menuItems.getNameOfWeek()))
                .spiceLevel(menuItems.getSpiceLevel())
                .isVegetarian(menuItems.getIsVegetarian())
                .categoryId(menuItems.getCategory().getId())
                .isLactoseFree(menuItems.getIsLactoseFree())
                .image(menuItems.getImage())
                .build();
    }
}
