package com.shankar.intelligentrestaurantmanagementsystem.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String userNotFound) {
        throw new RuntimeException(userNotFound);
    }
}
