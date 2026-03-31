package com.shankar.intelligentrestaurantmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "menu_items", indexes = {
        @Index(name = "idx_item_category", columnList = "category_id"),
        @Index(name = "idx_item_name_en", columnList = "item_name_en"),
        @Index(name = "idx_item_name_fi", columnList = "item_name_fi"),
        @Index(name = "idx_item_active", columnList = "is_active"),
        @Index(name = "idx_item_deleted", columnList = "is_deleted")
}
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String image;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "en", column = @Column(name = "item_name_en", nullable = false)),
            @AttributeOverride(name = "fi", column = @Column(name = "item_name_fi", nullable = false))
    })
    private Language itemName;

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

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    @Column(name = "vat_percent", nullable = false)
    private Integer vatPercent = 14;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "is_gluten_free", nullable = false)
    private Boolean isGlutenFree = false;

    @Column(name = "is_lactose_free", nullable = false)
    private Boolean isLactoseFree = false;

    @Column(name = "currency", nullable = false)
    private String currency = "€";

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "is_day_of_week", nullable = false)
    private Boolean isDayOfWeek = false;

    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek = 0;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "en", column = @Column(name = "name_of_week_en")),
            @AttributeOverride(name = "fi", column = @Column(name = "name_of_week_fi"))
    })
    private Language nameOfWeek;

    @Column(name = "is_spicy", nullable = false)
    private Boolean isSpicy = false;

    @Column(name = "spice_level", nullable = false)
    private Integer spiceLevel = 0;

    @Column(name = "is_vegan", nullable = false)
    private Boolean isVegan = false;

    @Column(name = "is_vegetarian", nullable = false)
    private Boolean isVegetarian = false;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;

}
