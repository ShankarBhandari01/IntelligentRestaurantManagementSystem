package com.shankar.intelligentrestaurantmanagementsystem.entity;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotItemStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "kot_items",
        indexes = {
                @Index(name = "idx_kot_item_kot", columnList = "kot_id"),
                @Index(name = "idx_kot_item_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kot_id", nullable = false)
    private Kot kot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItems orderItem;

    private int quantity;

    private String specialRequests;

    @Enumerated(EnumType.STRING)
    private KotItemStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
