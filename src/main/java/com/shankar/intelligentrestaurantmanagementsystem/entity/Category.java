package com.shankar.intelligentrestaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "categories",
        indexes = {
                @Index(name = "idx_category_name_en", columnList = "name_en"),
                @Index(name = "idx_category_name_fi", columnList = "name_fi")
        }
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String  id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "en", column = @Column(name = "name_en", nullable = false, unique = true)),
            @AttributeOverride(name = "fi", column = @Column(name = "name_fi", nullable = false, unique = true))
    })
    private Language name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "en", column = @Column(name = "description_en")),
            @AttributeOverride(name = "fi", column = @Column(name = "description_fi"))
    })
    private Language description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "en", column = @Column(name = "remarks_en")),
            @AttributeOverride(name = "fi", column = @Column(name = "remarks_fi"))
    })
    private Language remarks = new Language("new listed", "uusi listattu");

    @Column(name = "is_lunch_category", nullable = false)
    private Boolean isLunchCategory = false;

    @Column(name = "is_orderable", nullable = false)
    private Boolean isOrderable = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
