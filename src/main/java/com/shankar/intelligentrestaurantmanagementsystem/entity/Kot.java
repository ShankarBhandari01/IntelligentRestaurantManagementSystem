package com.shankar.intelligentrestaurantmanagementsystem.entity;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Table(name = "kots")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kot {

    @Id
    @GeneratedValue
    private Long id;

    private Long orderId;

    private String deviceId;
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private KotStatus status;  // CREATED, SENT, PRINTED, FAILED

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kot_id")
    private List<KotItem> items;
}
