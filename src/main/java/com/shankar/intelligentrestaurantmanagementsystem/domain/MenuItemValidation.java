package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.MenuItemRequest;
import com.shankar.intelligentrestaurantmanagementsystem.exception.ApiException;

public class MenuItemValidation {
    private final MenuItemRequest requestedMenuItem;

    public MenuItemValidation(MenuItemRequest requestedMenuItem) {
        this.requestedMenuItem = requestedMenuItem;
    }

    public void validateMenuItem() {
        if (requestedMenuItem.getQuantity() < 0 || requestedMenuItem.getQuantity() == 0) {
            throw new ApiException("Quantity must be greater than 0");
        } else if (requestedMenuItem.getAmount().doubleValue() < 0 || requestedMenuItem.getAmount().doubleValue() == 0) {
            throw new ApiException("Amount must be greater than 0");
        }
    }

}
