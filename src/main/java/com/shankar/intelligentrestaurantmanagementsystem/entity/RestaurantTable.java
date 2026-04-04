package com.shankar.intelligentrestaurantmanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class RestaurantTable {
    @Id
    private Long id;

}
