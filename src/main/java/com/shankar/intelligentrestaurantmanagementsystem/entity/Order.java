package com.shankar.intelligentrestaurantmanagementsystem.entity;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import com.shankar.intelligentrestaurantmanagementsystem.dto.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "orders")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderRemarks;
    private String orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderItems> items;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
