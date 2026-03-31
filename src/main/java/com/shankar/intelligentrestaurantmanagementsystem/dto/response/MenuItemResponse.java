package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.LanguageDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemResponse {
    private String id;
    private String categoryId;
    private String image;
    private LanguageDto itemName;
    private LanguageDto description;
    private LanguageDto remarks;

    private Integer quantity;

    private Integer vatPercent;


    private BigDecimal amount;

    private Boolean isGlutenFree;

    private Boolean isLactoseFree;

    private String currency;

    private Boolean isActive;

    private Boolean isDeleted;

    private Boolean isDayOfWeek;

    private Integer dayOfWeek;

    private LanguageDto nameOfWeek;

    private Boolean isSpicy;

    private Integer spiceLevel;

    private Boolean isVegan;

    private Boolean isVegetarian;
}
