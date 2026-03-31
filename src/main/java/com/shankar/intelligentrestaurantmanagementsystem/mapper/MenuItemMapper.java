package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.MenuItemResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.MenuItems;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper extends BaseMapper {

    public MenuItems toEntity(MenuItemRequest menuItemsRequest) {
        return MenuItems.builder()
                .itemName(mapToLanguage(menuItemsRequest.getItemName()))
                .description(mapToLanguage(menuItemsRequest.getDescription()))
                .remarks(mapToLanguage(menuItemsRequest.getRemarks()))
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
                .nameOfWeek(mapToLanguage(menuItemsRequest.getNameOfWeek()))
                .spiceLevel(menuItemsRequest.getSpiceLevel())
                .isVegetarian(menuItemsRequest.getIsVegetarian())
                .isLactoseFree(menuItemsRequest.getIsLactoseFree())
                .image(menuItemsRequest.getImage()) // mode to es2
                .build();
    }

    public MenuItemResponse toResponse(MenuItems menuItems) {
        return MenuItemResponse.builder()
                .id(menuItems.getId())
                .itemName(mapToLanguageDto(menuItems.getItemName()))
                .description(mapToLanguageDto(menuItems.getDescription()))
                .remarks(mapToLanguageDto(menuItems.getRemarks()))
                .quantity(menuItems.getQuantity())
                .vatPercent(menuItems.getVatPercent())
                .amount(menuItems.getAmount())
                .isSpicy(menuItems.getIsSpicy())
                .isGlutenFree(menuItems.getIsGlutenFree())
                .isVegan(menuItems.getIsVegan())
                .isActive(menuItems.getIsActive())
                .isDeleted(menuItems.getIsDeleted())
                .currency(menuItems.getCurrency())
                .isDayOfWeek(menuItems.getIsDayOfWeek())
                .dayOfWeek(menuItems.getDayOfWeek())
                .nameOfWeek(mapToLanguageDto(menuItems.getNameOfWeek()))
                .spiceLevel(menuItems.getSpiceLevel())
                .isVegetarian(menuItems.getIsVegetarian())
                .categoryId(menuItems.getCategory().getId())
                .isLactoseFree(menuItems.getIsLactoseFree())
                .image(menuItems.getImage())
                .build();
    }
}
