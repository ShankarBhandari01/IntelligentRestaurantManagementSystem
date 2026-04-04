package com.shankar.intelligentrestaurantmanagementsystem.dto;

public enum KotItemStatus {

    NEW,          // Just created / sent to kitchen
    IN_PROGRESS,  // Chef started preparing
    READY,        // Ready for pickup / serving
    SERVED,       // Delivered to customer
    CANCELLED     // Cancelled before completion
}
