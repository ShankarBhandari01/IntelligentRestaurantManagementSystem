package com.shankar.intelligentrestaurantmanagementsystem.dto.request;


import com.shankar.intelligentrestaurantmanagementsystem.dto.LanguageDto;
import io.github.resilience4j.core.lang.NonNull;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Valid
@Data
@RequiredArgsConstructor
public class MenuItemRequest {
    @NonNull
    private String categoryId;

    private String image;
    @NonNull
    private LanguageDto itemName;

    private LanguageDto description;
    private LanguageDto remarks = new LanguageDto("new listed", "uusi listattu");

    private Integer quantity = 0;

    private Integer vatPercent = 14;

    @NonNull
    private BigDecimal amount;

    private Boolean isGlutenFree = false;

    private Boolean isLactoseFree = false;

    private String currency = "€";

    private Boolean isActive = true;

    private Boolean isDeleted = false;

    private Boolean isDayOfWeek = false;

    private Integer dayOfWeek = 0;

    @NonNull
    private LanguageDto nameOfWeek;

    private Boolean isSpicy = false;

    private Integer spiceLevel = 0;

    private Boolean isVegan = false;

    private Boolean isVegetarian = false;

}
