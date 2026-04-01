package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.OrderRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.OrderResponse;
import com.shankar.intelligentrestaurantmanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest request)
            throws ExecutionException, InterruptedException {
        return created(orderService.processOrder(request).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id)
            throws ExecutionException, InterruptedException {
        return ok(orderService.getOrderById(id).get());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<?>>> getAllOrders()
            throws ExecutionException, InterruptedException {
        return ok(orderService.getAllOrders().get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request)

            throws ExecutionException, InterruptedException {
        return ok(orderService.updateOrder(id, request).get());
    }

}
