package com.shankar.intelligentrestaurantmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "kot_items")
public class KotItem {

    @Id
    @GeneratedValue
    private Long id;

    private String item;
    private int quantity;
    private String specialRequests;
}
