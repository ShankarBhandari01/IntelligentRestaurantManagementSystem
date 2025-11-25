package com.shankar.intelligentrestaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private String type;

    private boolean expired;

    private boolean revoked;

    private Date createdAt = new Date();

    private Date expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
