package com.shankar.intelligentrestaurantmanagementsystem.entity;

import com.shankar.intelligentrestaurantmanagementsystem.dto.KotStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String kotNumber;

    private String deviceId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @OneToMany(mappedBy = "kot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<KotItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private KotStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}